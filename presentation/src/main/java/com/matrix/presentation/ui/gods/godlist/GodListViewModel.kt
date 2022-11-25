package com.matrix.presentation.ui.gods.godlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.Result
import com.matrix.domain.models.asResult
import com.matrix.domain.usecases.GetLatestGodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GodListViewModel @Inject constructor(
  getLatestGodsUseCase: GetLatestGodsUseCase
) : ViewModel() {

  val uiState = getLatestGodsUseCase().asResult().map { result ->
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
}

sealed interface GodListUiState {
  data class Success(
    val gods: List<GodInformation>,
  ) : GodListUiState

  data class Error(val exception: Throwable?) : GodListUiState
  object Loading : GodListUiState
}