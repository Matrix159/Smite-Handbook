package com.matrix.presentation.ui.builds.builddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.presentation.ui.builds.navigation.BuildsNavigation
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

//@HiltViewModel
class BuildDetailsViewModel /*@Inject*/ constructor(
  private val smiteRepository: SmiteRepository,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val buildId: Long =
    (checkNotNull(savedStateHandle[BuildsNavigation.BuildDetails.buildIdArg]) as String).toLong()

  val uiState = combine(
    smiteRepository.getBuild(buildId),
    smiteRepository.getGods(),
    smiteRepository.getItems(),
    ::Triple
  )
    .asResult()
    .map { result ->
      when (result) {
        is Result.Success -> BuildDetailsUiState.Success(
          buildInformation = result.data.first,
          allGods = result.data.second,
          allItems = result.data.third
        )
        is Result.Error -> {
          Timber.e(result.exception)
          BuildDetailsUiState.Error(Exception("An error occurred while loading build information."))
        }
        Result.Loading -> BuildDetailsUiState.Loading
      }
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = BuildDetailsUiState.Loading
    )

  suspend fun deleteBuild(buildInformation: BuildInformation) {
    smiteRepository.deleteBuild(buildInformation)
  }

  fun saveBuild(buildInformation: BuildInformation) = viewModelScope.launch {
    smiteRepository.createBuild(buildInformation)
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