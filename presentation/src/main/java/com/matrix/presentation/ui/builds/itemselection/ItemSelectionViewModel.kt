package com.matrix.presentation.ui.builds.itemselection

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class ItemSelectionViewModel(
  smiteRepository: SmiteRepository
): ViewModel() {

  private val selectedItems = mutableStateListOf<ItemInformation>()

  val uiState: StateFlow<ItemSelectionUiState> = combine(
    smiteRepository.getItems(),
    snapshotFlow { selectedItems },
    ::StateInputs
  ).asResult()
    .map { result ->
      when (result) {
        is Result.Success ->  {
          val inputs = result.data
          ItemSelectionUiState.Success(
            items = inputs.items,
            selectedItems = inputs.selectedItems
          )
        }
        is Result.Loading -> ItemSelectionUiState.Loading
        is Result.Error -> {
          Timber.e(result.exception)
          ItemSelectionUiState.Error(Exception("An error occurred while loading items."))
        }
      }
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = ItemSelectionUiState.Loading
    )

  fun addItem(item: ItemInformation) {
    selectedItems.add(item)
  }

  fun removeItem(item: ItemInformation) {
    selectedItems.remove(item)
  }
}

sealed interface ItemSelectionUiState {
  data class Success(
    val items: List<ItemInformation>,
    val selectedItems: List<ItemInformation>
  ) : ItemSelectionUiState

  data class Error(val exception: Throwable?) : ItemSelectionUiState
  data object Loading : ItemSelectionUiState
}

private data class StateInputs(
  val items: List<ItemInformation>,
  val selectedItems: List<ItemInformation>
)