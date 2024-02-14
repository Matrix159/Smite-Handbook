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
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class BuildDetailsViewModel(
  private val smiteRepository: SmiteRepository,
  savedStateHandle: SavedStateHandle,
  selectedGodId: Flow<Long?>,
  selectedItemIds: Flow<List<Long>>
) : ViewModel() {
  private val buildId: Long =
    (checkNotNull(savedStateHandle[BuildsNavigation.BuildDetails.buildIdArg]) as String).toLong()

  init {
    selectedGodId
      .filterNotNull()
      .onEach {
        smiteRepository.updateGodInBuild(buildId, it)
      }.launchIn(viewModelScope)
    selectedItemIds
      .onEach { itemIds ->
        if (itemIds.isNotEmpty()) {
          smiteRepository.updateItemsInBuild(buildId, itemIds)
        }
      }
      .launchIn(viewModelScope)
  }

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
          // Ignoring no such element exception when logging as a deleted build triggers this
          if (result.exception !is NoSuchElementException) {
            Timber.e(result.exception)
          }
          BuildDetailsUiState.Error(Exception("An error occurred while loading build information."))
        }
        Result.Loading -> BuildDetailsUiState.Loading
      }
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = BuildDetailsUiState.Loading
    )

  fun deleteBuild(buildInformation: BuildInformation) = viewModelScope.launch {
    smiteRepository.deleteBuild(buildInformation)
  }

  fun saveBuild(buildInformation: BuildInformation) = viewModelScope.launch {
    smiteRepository.saveBuild(buildInformation)
  }
}

sealed interface BuildDetailsUiState {
  data class Success(
    val buildInformation: BuildInformation,
    val allGods: List<GodInformation>,
    val allItems: List<ItemInformation>,
    val isEditing: Boolean = false,
  ) : BuildDetailsUiState

  data class Error(val exception: Throwable?) : BuildDetailsUiState
  data object Loading : BuildDetailsUiState
}