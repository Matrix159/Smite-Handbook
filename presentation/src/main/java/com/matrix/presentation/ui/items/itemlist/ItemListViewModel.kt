package com.matrix.presentation.ui.items.itemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

//@HiltViewModel
class ItemListViewModel /*@Inject*/ constructor(
  smiteRepository: SmiteRepository,
) : ViewModel() {

  var uiState: StateFlow<ItemListUiState> = smiteRepository.getItems().asResult()
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
          Timber.e(result.exception)
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

