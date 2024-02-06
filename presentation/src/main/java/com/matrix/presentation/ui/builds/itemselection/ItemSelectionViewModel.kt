package com.matrix.presentation.ui.builds.itemselection

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.matrix.presentation.models.filters.AppliedItemFilters
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

@OptIn(SavedStateHandleSaveableApi::class)
class ItemSelectionViewModel(
  smiteRepository: SmiteRepository,
  savedStateHandle: SavedStateHandle
): ViewModel() {

  private var appliedItemFilters by savedStateHandle.saveable {
    mutableStateOf(AppliedItemFilters())
  }

  private var selectedItemIds by savedStateHandle.saveable {
    mutableStateOf<List<Long>>(emptyList())
  }

  val uiState: StateFlow<ItemSelectionUiState> = combine(
    smiteRepository.getItems(),
    snapshotFlow { selectedItemIds },
    snapshotFlow { appliedItemFilters },
    ::StateInputs
  ).asResult()
    .map { result ->
      when (result) {
        is Result.Success ->  {
          val inputs = result.data
          ItemSelectionUiState.Success(
            items = inputs.items,
            selectedItems = inputs.items.filter { item -> inputs.selectedItemIds.contains(item.itemID) },
            appliedItemFilters = appliedItemFilters
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
    selectedItemIds = selectedItemIds + item.itemID
  }

  fun removeItem(item: ItemInformation) {
    selectedItemIds = selectedItemIds - item.itemID
  }

  fun updateAppliedFilters(newFilters: AppliedItemFilters) {
    appliedItemFilters = newFilters
  }
}

sealed interface ItemSelectionUiState {
  data class Success(
    val items: List<ItemInformation> = emptyList(),
    val selectedItems: List<ItemInformation> = emptyList(),
    val appliedItemFilters: AppliedItemFilters = AppliedItemFilters()
  ) : ItemSelectionUiState

  data class Error(val exception: Throwable?) : ItemSelectionUiState
  data object Loading : ItemSelectionUiState
}

private data class StateInputs(
  val items: List<ItemInformation>,
  val selectedItemIds: List<Long>,
  val appliedItemFilters: AppliedItemFilters
)