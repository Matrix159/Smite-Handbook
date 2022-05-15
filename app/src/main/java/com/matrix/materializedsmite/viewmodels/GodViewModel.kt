package com.matrix.materializedsmite.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.api.models.GodInformation
import com.matrix.api.models.GodSkin
import com.matrix.api.models.Item
import com.matrix.materializedsmite.repositories.smite.SmiteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GodViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository
) : ViewModel() {


  private val _gods = MutableStateFlow<List<GodInformation>>(listOf())
  val gods: StateFlow<List<GodInformation>> = _gods

  private val _selectedGod = MutableStateFlow<GodInformation?>(null)
  val selectedGod: StateFlow<GodInformation?> = _selectedGod

  private val _selectedGodSkins = MutableStateFlow<List<GodSkin>>(listOf())
  val selectedGodSkins: StateFlow<List<GodSkin>> = _selectedGodSkins


  init {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        _gods.value = smiteRepo.getGods()
      } catch (ex: Exception) {
        Log.e("GodViewModel", ex.toString())
      }
      _selectedGod.collect {
        try {
          it?.let {
            _selectedGodSkins.value = smiteRepo.getGodSkins(it.id)
          }
        } catch (ex: Exception) {
          Log.e("GodViewModel", ex.toString())
        }
      }
    }
  }

  suspend fun getGods() {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        _gods.value = smiteRepo.getGods()
      } catch (ex: Exception) {
        Log.e("SmiteViewModel", ex.toString())
      }
    }
  }

  fun setGod(godInformation: GodInformation) {
    _selectedGod.value = godInformation
  }
}