package com.matrix.materializedsmite.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.matrix.materializedsmite.data.models.GodInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SmiteAppViewModel @Inject constructor() : ViewModel() {

  private val _selectedGod: MutableState<GodInformation?> = mutableStateOf(null)
  val selectedGod: State<GodInformation?>
    get() = _selectedGod

  fun setGod(godInformation: GodInformation) {
    _selectedGod.value = godInformation
  }
}