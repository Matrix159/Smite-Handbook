package com.matrix.presentation.ui.builds.createbuild

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.BuildInformation
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.ItemInformation
import com.matrix.domain.models.Result
import com.matrix.domain.models.asResult
import com.matrix.domain.usecases.BuildsUseCase
import com.matrix.domain.usecases.GetLatestGodsUseCase
import com.matrix.domain.usecases.GetLatestItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateBuildViewModel @Inject constructor(
  private val buildsUseCase: BuildsUseCase,
  private val getLatestGodsUseCase: GetLatestGodsUseCase,
  private val getLatestItemsUseCase: GetLatestItemsUseCase,
) : ViewModel() {

  private var selectedGod by mutableStateOf<GodInformation?>(null)
  private var selectedItems = mutableStateListOf<ItemInformation>()
  private var buildName by mutableStateOf("")

  // The UI collects from this StateFlow to get its state updates
  val uiState: StateFlow<CreateBuildUiState> = combine(
    getLatestGodsUseCase().asResult(),
    getLatestItemsUseCase().asResult(),
    snapshotFlow { selectedGod },
    snapshotFlow { selectedItems },
    snapshotFlow { buildName }
  ) { latestGods, latestItems, selectedGod, selectedItems, buildName ->
    if (latestGods is Result.Success && latestItems is Result.Success) {
      CreateBuildUiState.Success(
        gods = latestGods.data.sortedBy { it.name },
        items = latestItems.data.sortedBy { it.deviceName },
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
    // Builds should only have 6 total items and no duplicates
    if (selectedItems.size < 6 && !selectedItems.contains(item)) {
      selectedItems.add(item)
    }
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

  fun createBuild() = viewModelScope.launch {
    if (selectedGod != null && selectedItems.isNotEmpty()) {
      // TODO REMOVE
      repeat(50) {
        Timber.d("Is it 50 times")
        buildsUseCase.createBuild(
          BuildInformation(
            god = selectedGod!!,
            name = buildName,
            items = selectedItems
          )
        )
      }
    }
  }
}

// Represents different states for the Builds screen
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
