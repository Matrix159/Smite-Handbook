package com.matrix.presentation.ui.items.itemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.ItemInformation
import com.matrix.domain.models.Result
import com.matrix.domain.models.asResult
import com.matrix.domain.usecases.GetLatestItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
  getLatestItemsUseCase: GetLatestItemsUseCase,
) : ViewModel() {

  var uiState: StateFlow<ItemListUiState> = getLatestItemsUseCase().asResult()
    .map { result ->
      when (result) {
        is Result.Success -> {
          when (result.data.isEmpty()) {
            true -> ItemListUiState.Loading
            false -> ItemListUiState.Success(
              items = result.data
            )
          }
        }

        is Result.Loading -> {
          ItemListUiState.Loading
        }

        is Result.Error -> {
          ItemListUiState.Error(result.exception)
        }
      }
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = ItemListUiState.Loading
    )

}

sealed interface ItemListUiState {
  data class Success(val items: List<ItemInformation>) : ItemListUiState

  data class Error(val exception: Throwable?) : ItemListUiState
  object Loading : ItemListUiState
}

