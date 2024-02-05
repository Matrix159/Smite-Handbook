package com.matrix.presentation.ui.builds.createbuild

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class CreateBuildViewModel(
  private val smiteRepository: SmiteRepository,
  val savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private val selectedGodId: StateFlow<Long?> = savedStateHandle.getStateFlow(BuildsNavigation.GodList.selectedGodId, null)

  private var selectedItems = mutableStateListOf<ItemInformation>()
  private var buildName by mutableStateOf("")
//  private var appliedGodFilters by mutableStateOf(AppliedGodFilters())

  private var uiInputs = derivedStateOf {
    UIInputs(
      selectedItems = selectedItems,
      buildName = buildName
//      appliedGodFilters = appliedGodFilters
    )
  }

  // The UI collects from this StateFlow to get its state updates
  val uiState: StateFlow<CreateBuildUiState> = combine(
    smiteRepository.getGods(),
    smiteRepository.getItems(),
    selectedGodId,
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
          selectedItems = inputs.uiInput.selectedItems,
          buildName = inputs.uiInput.buildName,
//          appliedGodFilters = inputs.uiInput.appliedGodFilters
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

  fun addSelectedItem(item: ItemInformation) {
    selectedItems.add(item)
  }

  fun removeSelectedItem(item: ItemInformation) {
    selectedItems.remove(item)
  }

  fun clearSelectedItems() {
    selectedItems.clear()
  }

  fun updateBuildName(name: String) {
    buildName = name
  }

  suspend fun createBuild() {
    val uiState = uiState.value
    if (selectedItems.isNotEmpty() && uiState is CreateBuildUiState.Success && uiState.selectedGod != null) {
      smiteRepository.createBuild(
        BuildInformation(
          god = uiState.selectedGod,
          name = when {
            buildName.isEmpty() -> "${uiState.selectedGod.name}'s build"
            else -> buildName
          },
          items = selectedItems
        )
      )
    }
  }

//  fun updateAppliedFilters(newFilters: AppliedGodFilters) {
//    appliedGodFilters = newFilters
//  }
}

data class UIInputs(
//  val selectedGod: GodInformation?,
  val selectedItems: List<ItemInformation>,
  val buildName: String
//  val appliedGodFilters: AppliedGodFilters
)

data class StateInputs(
  val gods: List<GodInformation>,
  val items: List<ItemInformation>,
  val selectedGodId: Long?,
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
