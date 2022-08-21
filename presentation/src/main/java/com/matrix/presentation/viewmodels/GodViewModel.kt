package com.matrix.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.usecases.GetGodSkinsUseCase
import com.matrix.domain.usecases.GetLatestGodsUseCase
import com.matrix.presentation.models.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

data class GodListUiState(
  val loadingState: LoadingState = LoadingState.LOADING,
  val gods: List<GodInformation> = listOf(),
  val errors: List<String> = listOf(),
)

data class GodDetailsUiState(
  val selectedGod: GodInformation? = null,
  val godSkins: List<GodSkin> = listOf(),
  val errors: List<String> = listOf(),
)

const val GOD_LIST_CACHE_KEY = "god_list_cache"

@HiltViewModel
class GodViewModel @Inject constructor(
  private val getLatestGodsUseCase: GetLatestGodsUseCase,
  private val getGodSkinsUseCase: GetGodSkinsUseCase
) : ViewModel() {

  var godListUiState by mutableStateOf(GodListUiState())
    private set

  var godDetailsUiState by mutableStateOf(GodDetailsUiState())
    private set


  init {
    viewModelScope.launch {
      Timber.d("loadState")
      godListUiState = godListUiState.copy(loadingState = LoadingState.LOADING)
      try {
        var gods: List<GodInformation>
        withContext(Dispatchers.IO) {
          gods = getLatestGodsUseCase()
        }

        godListUiState = godListUiState.copy(gods = gods, loadingState = LoadingState.DONE)
      } catch (ex: Exception) {
        godListUiState =
          godListUiState.copy(
            errors = listOf(ex.toString()),
            loadingState = LoadingState.ERROR
          )
        Timber.e(ex.toString())
      }
    }
  }

  suspend fun setGod(godInformation: GodInformation?) {
    try {
      when (godInformation) {
        null -> {
          godDetailsUiState = GodDetailsUiState()
        }
        else -> {
          godDetailsUiState = godDetailsUiState.copy(
            selectedGod = godInformation,
            godSkins = listOf()
          )
          var godSkins: List<GodSkin>
          // Load the skins asynchronously
          withContext(Dispatchers.IO) {
            godSkins = getGodSkinsUseCase(godInformation.id)
            godDetailsUiState =
              godDetailsUiState.copy(godSkins = godSkins)
          }
        }
      }
    } catch (ex: Exception) {
      godDetailsUiState = godDetailsUiState.copy(errors = listOf(ex.toString()))
      Timber.e(ex.toString())
    }
  }
}