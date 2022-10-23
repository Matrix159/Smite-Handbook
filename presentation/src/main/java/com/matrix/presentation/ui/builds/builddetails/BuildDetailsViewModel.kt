package com.matrix.presentation.ui.builds.builddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.BuildInformation
import com.matrix.domain.models.Result
import com.matrix.domain.models.asResult
import com.matrix.domain.usecases.BuildsUseCase
import com.matrix.presentation.ui.builds.navigation.BuildsNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BuildDetailsViewModel @Inject constructor(
  buildsUseCase: BuildsUseCase,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val buildId: Int =
    (checkNotNull(savedStateHandle[BuildsNavigation.BuildDetails.buildIdArg]) as String).toInt()

  val uiState = buildsUseCase
    .getBuild(buildId)
    .asResult()
    .map { result ->
      when (result) {
        Result.Loading -> BuildDetailsUiState.Loading
        is Result.Error -> BuildDetailsUiState.Error(result.exception)
        is Result.Success -> BuildDetailsUiState.Success(result.data)
      }
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = BuildDetailsUiState.Loading
    )
}

sealed interface BuildDetailsUiState {
  data class Success(
    val buildInformation: BuildInformation,
  ) : BuildDetailsUiState

  data class Error(val exception: Throwable?) : BuildDetailsUiState
  object Loading : BuildDetailsUiState
}