package com.matrix.presentation.ui.gods.godlist

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.model.gods.GodInformation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

@OptIn(SavedStateHandleSaveableApi::class)
class GodListViewModel (
  smiteRepository: SmiteRepository,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private var appliedGodFilters by savedStateHandle.saveable {
    mutableStateOf(AppliedGodFilters())
  }

  val uiState = combine(
    smiteRepository.getGods().asResult(),
    snapshotFlow { appliedGodFilters },
  ) { godsResult , appliedGodFilters ->
    when (godsResult) {
      is Result.Success -> {
        when (godsResult.data.isEmpty()) {
          true -> GodListUiState.Loading
          false -> GodListUiState.Success(
            gods = godsResult.data,
            appliedGodFilters = appliedGodFilters
          )
        }
      }
      is Result.Loading -> {
        GodListUiState.Loading
      }
      is Result.Error -> {
        Timber.e(godsResult.exception)
        GodListUiState.Error(godsResult.exception)
      }
    }
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = GodListUiState.Loading
  )

  fun updateAppliedFilters(newFilters: AppliedGodFilters) {
    appliedGodFilters = newFilters
  }
}

sealed interface GodListUiState {
  data class Success(
    val gods: List<GodInformation> = emptyList(),
    val appliedGodFilters: AppliedGodFilters = AppliedGodFilters()
  ) : GodListUiState

  data class Error(val exception: Throwable?) : GodListUiState
  data object Loading : GodListUiState
}