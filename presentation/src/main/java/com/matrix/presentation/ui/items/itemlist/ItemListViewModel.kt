package com.matrix.presentation.ui.items.itemlist

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.matrix.presentation.models.filters.AppliedGodFilters
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
class ItemListViewModel(
  smiteRepository: SmiteRepository,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private var appliedItemFilters by savedStateHandle.saveable {
    mutableStateOf(AppliedItemFilters())
  }

  var uiState: StateFlow<ItemListUiState> =  combine(
    smiteRepository.getItems().asResult(),
    snapshotFlow { appliedItemFilters },
  ) { result , appliedItemFilters ->
      when (result) {
        is Result.Success -> {
          when (result.data.isEmpty()) {
            true -> ItemListUiState.Loading
            false -> ItemListUiState.Success(
              items = result.data,
              appliedItemFilters = appliedItemFilters
            )
          }
        }

        is Result.Loading -> {
          ItemListUiState.Loading
        }

        is Result.Error -> {
          Timber.e(result.exception)
          ItemListUiState.Error(result.exception)
        }
      }
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = ItemListUiState.Loading
    )

  fun updateAppliedFilters(newFilters: AppliedItemFilters) {
    appliedItemFilters = newFilters
  }
}

sealed interface ItemListUiState {
  data class Success(
    val items: List<ItemInformation> = emptyList(),
    val appliedItemFilters: AppliedItemFilters = AppliedItemFilters()
  ) : ItemListUiState

  data class Error(val exception: Throwable?) : ItemListUiState
  data object Loading : ItemListUiState
}

