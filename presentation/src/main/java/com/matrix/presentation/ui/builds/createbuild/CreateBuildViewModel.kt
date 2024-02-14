package com.matrix.presentation.ui.builds.createbuild

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.presentation.ui.builds.navigation.BuildsNavigation
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class CreateBuildViewModel(
  private val smiteRepository: SmiteRepository,
  selectedGodId: Flow<Long?>,
  selectedItemIds: Flow<List<Long>>
) : ViewModel() {

  private var buildName by mutableStateOf("")

  private var uiInputs = derivedStateOf {
    UIInputs(
      buildName = buildName
    )
  }

  // The UI collects from this StateFlow to get its state updates
  val uiState: StateFlow<CreateBuildUiState> = combine(
    smiteRepository.getGods(),
    smiteRepository.getItems(),
    selectedGodId,
    selectedItemIds,
    snapshotFlow { uiInputs.value },
    ::StateInputs
  ).asResult()
    .map { result ->
    when (result) {
      is Result.Success ->  {
        val inputs = result.data
        CreateBuildUiState.Success(
          gods = inputs.gods,
          items = inputs.items,
          selectedGod = inputs.gods.firstOrNull { it.id == inputs.selectedGodId },
          selectedItems = inputs.selectedItemIds.map { itemId -> inputs.items.first { it.itemID == itemId} },
          buildName = inputs.uiInput.buildName,
        )
      }
      is Result.Loading -> CreateBuildUiState.Loading
      is Result.Error -> {
        Timber.e(result.exception)
        CreateBuildUiState.Error(Exception("An error occurred while loading god and item data."))
      }
    }
  }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = CreateBuildUiState.Loading
    )

  fun updateBuildName(name: String) {
    buildName = name
  }

  suspend fun createBuild() {
    val uiState = uiState.value
    if (uiState is CreateBuildUiState.Success && uiState.selectedGod != null && uiState.selectedItems.isNotEmpty()) {
      smiteRepository.saveBuild(
        BuildInformation(
          god = uiState.selectedGod,
          name = when {
            buildName.isEmpty() -> "${uiState.selectedGod.name}'s build"
            else -> buildName
          },
          items = uiState.selectedItems
        )
      )
    }
  }
}

data class UIInputs(
  val buildName: String
)

data class StateInputs(
  val gods: List<GodInformation>,
  val items: List<ItemInformation>,
  val selectedGodId: Long?,
  val selectedItemIds: List<Long>,
  val uiInput: UIInputs,
)

sealed interface CreateBuildUiState {
  data class Success(
    val gods: List<GodInformation> = emptyList(),
    val items: List<ItemInformation> = emptyList(),
    val buildName: String = "",
    val selectedGod: GodInformation? = null,
    val selectedItems: List<ItemInformation> = emptyList(),
    val appliedGodFilters: AppliedGodFilters = AppliedGodFilters()
  ) : CreateBuildUiState

  data class Error(val exception: Throwable?) : CreateBuildUiState
  data object Loading : CreateBuildUiState
}
