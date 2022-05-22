package com.matrix.materializedsmite.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.api.models.GodInformation
import com.matrix.api.models.GodSkin
import com.matrix.materializedsmite.repositories.smite.SmiteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class GodListUiState(
  val gods: List<GodInformation> = listOf()
)

data class GodDetailsUiState(
  val selectedGod: GodInformation? = null,
  val godSkins: List<GodSkin> = listOf()
)

@HiltViewModel
class GodViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository,
) : ViewModel(), CanError {

  private val _godListUiState = MutableStateFlow(GodListUiState())
  val godListUiState: StateFlow<GodListUiState> = _godListUiState.asStateFlow()

  private val _godDetailsUiState = MutableStateFlow(GodDetailsUiState())
  val godDetailsUiState: StateFlow<GodDetailsUiState> = _godDetailsUiState

  private val _gods = MutableStateFlow<List<GodInformation>>(listOf())
  val gods: StateFlow<List<GodInformation>> = _gods.asStateFlow()

  private val _selectedGod = MutableStateFlow<GodInformation?>(null)
  //val selectedGod: StateFlow<GodInformation?> = _selectedGod.asStateFlow()

  init {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        val godsApiResult = smiteRepo.getGods()
        _godListUiState.update { GodListUiState(gods = godsApiResult) }
        error = null
      } catch (ex: Exception) {
        error = ex
        Log.e("GodViewModel", ex.toString())
      }

    }
    _selectedGod.onEach { flowGodInformation ->
      try {
        flowGodInformation?.let { selectedGod ->
          var godSkins: List<GodSkin>
          withContext(Dispatchers.IO) {
            godSkins =  smiteRepo.getGodSkins(selectedGod.id)
          }
          _godDetailsUiState.update { it.copy(selectedGod = selectedGod, godSkins = godSkins)}
        }
        error = null
      } catch (ex: Exception) {
        error = ex
        Log.e("GodViewModel", ex.toString())
      }
    }.launchIn(viewModelScope)
//   viewModelScope.launch(Dispatchers.IO) {
//     _selectedGod.collect {
//       try {
//         it?.let {
//           _selectedGodSkins.value = smiteRepo.getGodSkins(it.id)
//           error = null
//         }
//       } catch (ex: Exception) {
//         error = ex
//         Log.e("GodViewModel", ex.toString())
//       }
//     }
//   }
  }

  fun setGod(godInformation: GodInformation) {
    _selectedGod.update { godInformation }
  }

  fun clearSelectedGod() {
    _selectedGod.update { null }
  }

  override var error: Exception? = null
}