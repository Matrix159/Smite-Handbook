package com.matrix.presentation.ui.gods.goddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.presentation.ui.gods.navigation.GodsNavigation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.skins.GodSkinInformation
import com.matrix.shared.data.model.Result
import com.matrix.shared.data.model.asResult
import com.matrix.shared.data.usecases.GetGodSkinsUseCase
import com.matrix.shared.data.usecases.GetGodUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

/*@HiltViewModel*/
class GodDetailsViewModel /*@Inject*/ constructor(
  getGodUseCase: GetGodUseCase = GetGodUseCase(),
  getGodSkinsUseCase: GetGodSkinsUseCase = GetGodSkinsUseCase(),
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