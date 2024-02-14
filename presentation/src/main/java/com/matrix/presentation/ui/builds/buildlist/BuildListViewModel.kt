package com.matrix.presentation.ui.builds.buildlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.model.builds.BuildInformation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class BuildListViewModel(
  private val smiteRepository: SmiteRepository
) : ViewModel() {

  // The UI collects from this StateFlow to get its state updates
  val uiState: StateFlow<BuildsUiState> = smiteRepository
    .getBuilds()
    .asResult()
    .map { result ->
      when (result) {
        is Result.Success -> {
          BuildsUiState.Success(
            result.data
          )
        }
        is Result.Loading -> {
          BuildsUiState.Loading
        }
        is Result.Error -> {
          Timber.e(result.exception)
          BuildsUiState.Error(result.exception)
        }
      }
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = BuildsUiState.Loading
    )

  fun deleteBuild(buildInformation: BuildInformation) = viewModelScope.launch {
    smiteRepository.deleteBuild(buildInformation)
  }

  fun addBuild(buildInformation: BuildInformation) = viewModelScope.launch {
    smiteRepository.saveBuild(buildInformation)
  }
}

// Represents different states for the Builds screen
sealed interface BuildsUiState {
  data class Success(val builds: List<BuildInformation>) : BuildsUiState
  data class Error(val exception: Throwable?) : BuildsUiState
  object Loading : BuildsUiState
}
