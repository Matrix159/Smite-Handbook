package com.matrix.presentation.ui.builds.createbuild

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

//@HiltViewModel
class CreateBuildViewModel /*@Inject*/ constructor(
  private val smiteRepository: SmiteRepository
) : ViewModel() {

  private var selectedGod by mutableStateOf<GodInformation?>(null)
  private var selectedItems = mutableStateListOf<ItemInformation>()
  private var buildName by mutableStateOf("")

  // The UI collects from this StateFlow to get its state updates
  val uiState: StateFlow<CreateBuildUiState> = combine(
    smiteRepository.getGods().asResult(),
    smiteRepository.getItems().asResult(),
    snapshotFlow { selectedGod },
    snapshotFlow { selectedItems },
    snapshotFlow { buildName }
  ) { latestGods, latestItems, selectedGod, selectedItems, buildName ->
    if (latestGods is Result.Success && latestItems is Result.Success) {
      CreateBuildUiState.Success(
        gods = latestGods.data,
        items = latestItems.data,
        selectedGod = selectedGod,
        selectedItems = selectedItems,
        buildName = buildName
      )
    } else if (latestGods is Result.Loading || latestItems is Result.Loading) {
      CreateBuildUiState.Loading
    } else if (latestGods is Result.Error && latestItems is Result.Error) {
      CreateBuildUiState.Error(Exception("An error occurred while loading god and item data."))
    } else {
      CreateBuildUiState.Error(null)
    }
  }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = CreateBuildUiState.Loading
    )

  fun setGod(god: GodInformation) {
    selectedGod = god
  }

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
    if (selectedGod != null && selectedItems.isNotEmpty()) {
      smiteRepository.createBuild(
        BuildInformation(
          god = selectedGod!!,
          name = when {
            buildName.isEmpty() -> "${selectedGod!!.name}'s build"
            else -> buildName
          },
          items = selectedItems
        )
      )
    }
  }
}

sealed interface CreateBuildUiState {
  data class Success(
    val gods: List<GodInformation> = emptyList(),
    val items: List<ItemInformation> = emptyList(),
    val buildName: String = "",
    val selectedGod: GodInformation? = null,
    val selectedItems: List<ItemInformation> = emptyList(),
  ) : CreateBuildUiState

  data class Error(val exception: Throwable?) : CreateBuildUiState
  object Loading : CreateBuildUiState
}
