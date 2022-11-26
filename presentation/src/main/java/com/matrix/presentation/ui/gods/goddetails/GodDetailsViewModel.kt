package com.matrix.presentation.ui.gods.goddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix159.shared.data.models.GodInformation
import com.matrix159.shared.data.models.GodSkinInformation
import com.matrix159.shared.data.models.Result
import com.matrix159.shared.data.models.asResult
import com.matrix159.shared.data.usecases.GetGodSkinsUseCase
import com.matrix159.shared.data.usecases.GetGodUseCase
import com.matrix.presentation.ui.gods.navigation.GodsNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GodDetailsViewModel @Inject constructor(
  getGodUseCase: GetGodUseCase,
  getGodSkinsUseCase: GetGodSkinsUseCase,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val selectedGodId: Int = (checkNotNull(savedStateHandle[GodsNavigation.GodDetails.godIdArg]) as String).toInt()

  val godDetailsUiState = combine(
    getGodUseCase(selectedGodId),
    // Skins don't need to be loaded immediately
    getGodSkinsUseCase(selectedGodId).onStart { emit(emptyList()) },
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

sealed interface GodDetailsUiState {
  data class Success(val godInformation: GodInformation, val skins: List<GodSkinInformation>) :
    GodDetailsUiState

  data class Error(val exception: Throwable?) : GodDetailsUiState
  object Loading : GodDetailsUiState
}