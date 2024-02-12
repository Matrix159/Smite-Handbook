package com.matrix.presentation.ui.gods.goddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.presentation.ui.gods.navigation.GodsNavigation
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.skins.GodSkinInformation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class GodDetailsViewModel(
  smiteRepository: SmiteRepository,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val selectedGodId: Long =
    (checkNotNull(savedStateHandle[GodsNavigation.GodDetails.godIdArg]) as String).toLong()

  val uiState = combine(
    smiteRepository.getGod(selectedGodId),
    // Skins don't need to be loaded immediately
    smiteRepository.getGodSkins(selectedGodId).onStart { emit(emptyList()) },
    ::Pair
  ).asResult()
    .map { result ->
      when (result) {
        is Result.Success -> {
          GodDetailsUiState.Success(godInformation = result.data.first, skins = result.data.second)
        }

        is Result.Loading -> {
          GodDetailsUiState.Loading
        }

        is Result.Error -> {
          Timber.e(result.exception)
          GodDetailsUiState.Error
        }
      }
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = GodDetailsUiState.Loading
    )
}

sealed interface GodDetailsUiState {
  data class Success(val godInformation: GodInformation, val skins: List<GodSkinInformation>) :
    GodDetailsUiState

  data object Error : GodDetailsUiState
  data object Loading : GodDetailsUiState
}