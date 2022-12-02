package com.matrix.presentation.ui.gods.godlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

//@HiltViewModel
class GodListViewModel /*@Inject*/ (
  smiteRepository: SmiteRepository
) : ViewModel() {

  val uiState = smiteRepository.getGods().asResult().map { result ->
    when (result) {
      is Result.Success -> {
        when (result.data.isEmpty()) {
          true -> GodListUiState.Loading
          false -> GodListUiState.Success(
            gods = result.data
          )
        }
      }
      is Result.Loading -> {
        GodListUiState.Loading
      }
      is Result.Error -> {
        GodListUiState.Error(result.exception)
      }
    }
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = GodListUiState.Loading
  )


  // Define ViewModel factory in a companion object
//  companion object {
//    val Factory: ViewModelProvider.Factory = viewModelFactory {
//      initializer {
//        val savedStateHandle = createSavedStateHandle()
//        GodListViewModel()
//      }
//    }
//  }

}

sealed interface GodListUiState {
  data class Success(
    val gods: List<GodInformation>,
  ) : GodListUiState

  data class Error(val exception: Throwable?) : GodListUiState
  object Loading : GodListUiState
}