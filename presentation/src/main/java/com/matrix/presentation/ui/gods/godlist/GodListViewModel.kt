package com.matrix.presentation.ui.gods.godlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.matrix.shared.data.models.GodInformation
import com.matrix.shared.data.models.Result
import com.matrix.shared.data.models.asResult
import com.matrix.shared.data.usecases.GetLatestGodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

//@HiltViewModel
class GodListViewModel /*@Inject*/ constructor(

) : ViewModel() {
  //val getLatestGodsUseCase = GetLatestGodsUseCase()
//  val uiState = getLatestGodsUseCase().asResult().map { result ->
//    when (result) {
//      is Result.Success -> {
//        when (result.data.isEmpty()) {
//          true -> GodListUiState.Loading
//          false -> GodListUiState.Success(
//            gods = result.data
//          )
//        }
//      }
//      is Result.Loading -> {
//        GodListUiState.Loading
//      }
//      is Result.Error -> {
//        GodListUiState.Error(result.exception)
//      }
//    }
//  }.stateIn(
//    scope = viewModelScope,
//    started = SharingStarted.WhileSubscribed(5000),
//    initialValue = GodListUiState.Loading
//  )

  val uiState = flow<GodListUiState> {
    GodListUiState.Loading
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = GodListUiState.Loading
  )

  // Define ViewModel factory in a companion object
  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val savedStateHandle = createSavedStateHandle()
        GodListViewModel(
          //GetLatestGodsUseCase()
        )
      }
    }
  }

}

sealed interface GodListUiState {
  data class Success(
    val gods: List<GodInformation>,
  ) : GodListUiState

  data class Error(val exception: Throwable?) : GodListUiState
  object Loading : GodListUiState
}