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
  val uiState: StateFlow<BuildListUiState> = smiteRepository
    .getBuilds()
    .asResult()
    .map { result ->
      when (result) {
        is Result.Success -> {
          BuildListUiState.Success(
            result.data
          )
        }
        is Result.Loading -> {
          BuildListUiState.Loading
        }
        is Result.Error -> {
          Timber.e(result.exception)
          BuildListUiState.Error
        }
      }
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = BuildListUiState.Loading
    )

  fun deleteBuild(buildInformation: BuildInformation) = viewModelScope.launch {
    smiteRepository.deleteBuild(buildInformation)
  }

  fun addBuild(buildInformation: BuildInformation) = viewModelScope.launch {
    smiteRepository.saveBuild(buildInformation)
  }
}

// Represents different states for the Builds screen
sealed interface BuildListUiState {
  data class Success(val builds: List<BuildInformation>) : BuildListUiState
  data object Error : BuildListUiState
  data object Loading : BuildListUiState
}
