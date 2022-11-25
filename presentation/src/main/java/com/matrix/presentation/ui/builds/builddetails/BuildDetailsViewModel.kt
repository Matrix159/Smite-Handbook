package com.matrix.presentation.ui.builds.builddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.BuildInformation
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.ItemInformation
import com.matrix.domain.models.Result
import com.matrix.domain.models.asResult
import com.matrix.domain.usecases.BuildsUseCase
import com.matrix.domain.usecases.GetLatestGodsUseCase
import com.matrix.domain.usecases.GetLatestItemsUseCase
import com.matrix.presentation.ui.builds.navigation.BuildsNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildDetailsViewModel @Inject constructor(
  private val buildsUseCase: BuildsUseCase,
  getLatestGodsUseCase: GetLatestGodsUseCase,
  getLatestItemsUseCase: GetLatestItemsUseCase,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val buildId: Int =
    (checkNotNull(savedStateHandle[BuildsNavigation.BuildDetails.buildIdArg]) as String).toInt()

  val uiState = combine(
    buildsUseCase.getBuild(buildId).asResult(),
    getLatestGodsUseCase().asResult(),
    getLatestItemsUseCase().asResult(),
  ) { build, latestGods, latestItems ->
    if (build is Result.Success && latestGods is Result.Success && latestItems is Result.Success) {
      BuildDetailsUiState.Success(
        buildInformation = build.data,
        allGods = latestGods.data,
        allItems = latestItems.data
      )
    } else if (build is Result.Loading || latestGods is Result.Loading || latestItems is Result.Loading) {
      BuildDetailsUiState.Loading
    } else if (build is Result.Loading || latestGods is Result.Error && latestItems is Result.Error) {
      BuildDetailsUiState.Error(Exception("An error occurred while loading build information."))
    } else {
      BuildDetailsUiState.Error(null)
    }
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = BuildDetailsUiState.Loading
  )

  suspend fun deleteBuild(buildInformation: BuildInformation) {
    buildsUseCase.deleteBuild(buildInformation)
  }

  fun saveBuild(buildInformation: BuildInformation) = viewModelScope.launch {
    buildsUseCase.createBuild(buildInformation)
  }
}

sealed interface BuildDetailsUiState {
  data class Success(
    val buildInformation: BuildInformation,
    val allGods: List<GodInformation>,
    val allItems: List<ItemInformation>,
  ) : BuildDetailsUiState

  data class Error(val exception: Throwable?) : BuildDetailsUiState
  object Loading : BuildDetailsUiState
}