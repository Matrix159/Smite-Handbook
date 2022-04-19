package com.matrix.materializedsmite.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.matrix.materializedsmite.data.ApiResult
import com.matrix.materializedsmite.data.models.GodInformation
import com.matrix.materializedsmite.data.smite.SmiteRepository
import com.matrix.materializedsmite.data.successOr
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SmiteViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository
) : ViewModel() {

  private val _gods: MutableState<List<GodInformation>> = mutableStateOf(listOf())
  val gods: State<List<GodInformation>>
    get() = _gods

  private val _selectedGod: MutableState<GodInformation?> = mutableStateOf(null)
  val selectedGod: State<GodInformation?>
    get() = _selectedGod

  suspend fun getGods() {
    try {
      _gods.value = smiteRepo.getGods()
    } catch (ex: Exception) {
      Log.e("SmiteViewModel", ex.toString())
    }
  }

  fun setGod(godInformation: GodInformation) {
    _selectedGod.value = godInformation
  }

  fun goToPreviousGod() {
     selectedGod.value?.let {
       if (gods.value.isNotEmpty()) {
         val currentIndex = gods.value.indexOf(selectedGod.value)
         if (currentIndex - 1 >= 0) {
           _selectedGod.value = gods.value[currentIndex - 1]
         }
       }
     }
  }

  fun goToNextGod() {
    selectedGod.value?.let {
      if (gods.value.isNotEmpty()) {
        val currentIndex = gods.value.indexOf(selectedGod.value)
        if (currentIndex + 1 < gods.value.count()) {
          _selectedGod.value = gods.value[currentIndex + 1]
        }
      }
    }
  }
}