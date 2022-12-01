package com.matrix.presentation.ui.items.itemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.shared.data.models.ItemInformation
import com.matrix.shared.data.models.Result
import com.matrix.shared.data.models.asResult
import com.matrix.shared.data.usecases.GetLatestItemsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

//@HiltViewModel
class ItemListViewModel /*@Inject*/ constructor(
  getLatestItemsUseCase: GetLatestItemsUseCase = GetLatestItemsUseCase(),
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

