package com.matrix.presentation.ui.gods.goddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkinInformation
import com.matrix.domain.models.Result
import com.matrix.domain.models.asResult
import com.matrix.domain.usecases.GetGodSkinsUseCase
import com.matrix.domain.usecases.GetGodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GodDetailsViewModel @Inject constructor(
  getGodUseCase: GetGodUseCase,
  getGodSkinsUseCase: GetGodSkinsUseCase,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val selectedGodId: Int = (checkNotNull(savedStateHandle["godId"]) as String).toInt()

  val godDetailsUiState = combine(
    getGodUseCase(selectedGodId),
    getGodSkinsUseCase(selectedGodId),
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
          GodDetailsUiState.Error(result.exception)
        }
      }
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = GodDetailsUiState.Loading
    )
}

// Represents different states for the Builds screen
sealed interface GodDetailsUiState {
  data class Success(val godInformation: GodInformation, val skins: List<GodSkinInformation>) :
    GodDetailsUiState

  data class Error(val exception: Throwable?) : GodDetailsUiState
  object Loading : GodDetailsUiState
}