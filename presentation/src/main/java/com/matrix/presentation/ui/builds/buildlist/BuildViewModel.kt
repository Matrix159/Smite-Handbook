package com.matrix.presentation.ui.builds.buildlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix159.shared.data.models.BuildInformation
import com.matrix159.shared.data.models.Result
import com.matrix159.shared.data.models.asResult
import com.matrix159.shared.data.usecases.BuildsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BuildViewModel @Inject constructor(
  private val buildsUseCase: BuildsUseCase,
) : ViewModel() {

  // The UI collects from this StateFlow to get its state updates
  val uiState: StateFlow<BuildsUiState> = buildsUseCase
    .getBuilds()
    .asResult()
    .map { result ->
      when (result) {
        is Result.Success -> {
          BuildsUiState.Success(
            result.data.sortedBy { it.god.name }
          )
        }
        is Result.Loading -> {
          BuildsUiState.Loading
        }
        is Result.Error -> {
          BuildsUiState.Error(result.exception)
        }
      }
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = BuildsUiState.Loading
    )

  suspend fun deleteBuild(buildInformation: BuildInformation) {
    buildsUseCase.deleteBuild(buildInformation)
  }

  suspend fun addBuild(buildInformation: BuildInformation) {
    buildsUseCase.createBuild(buildInformation)
  }
}

// Represents different states for the Builds screen
sealed interface BuildsUiState {
  data class Success(val builds: List<BuildInformation>) : BuildsUiState
  data class Error(val exception: Throwable?) : BuildsUiState
  object Loading : BuildsUiState
}
