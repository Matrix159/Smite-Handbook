package com.matrix.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.BuildInformation
import com.matrix.domain.usecases.BuildsUseCase
import com.matrix.domain.usecases.GetLatestGodsUseCase
import com.matrix.domain.usecases.GetLatestItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class BuildViewModel @Inject constructor(
  private val buildsUseCase: BuildsUseCase,
  private val getLatestGodsUseCase: GetLatestGodsUseCase,
  private val getLatestItemsUseCase: GetLatestItemsUseCase
) : ViewModel() {

  // The UI collects from this StateFlow to get its state updates
  val uiState: StateFlow<BuildsUiState> = buildsUseCase
    .getBuilds()
    .map<List<BuildInformation>, BuildsUiState> {
      BuildsUiState.Success(it)
    }
    .catch {
      emit(BuildsUiState.Error(it))
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = BuildsUiState.Loading
    )

  suspend fun createBuild(/*buildInformation: BuildInformation*/) {
    // TODO: temporary
    val latestGods = getLatestGodsUseCase()
    val latestItems = getLatestItemsUseCase()
    buildsUseCase.createBuild(
      BuildInformation(
        god = latestGods[0],
        items = latestItems.take(6)
      )
    )
  }

  suspend fun deleteBuild(buildInformation: BuildInformation) {
    buildsUseCase.deleteBuild(buildInformation)
  }
}

// Represents different states for the Builds screen
sealed interface BuildsUiState {
  data class Success(val builds: List<BuildInformation>) : BuildsUiState
  data class Error(val exception: Throwable) : BuildsUiState
  object Loading : BuildsUiState
}
