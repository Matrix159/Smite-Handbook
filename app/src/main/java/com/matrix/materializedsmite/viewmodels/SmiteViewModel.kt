package com.matrix.materializedsmite.viewmodels

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

  suspend fun getGods() {
    val gods: ApiResult<List<GodInformation>> = smiteRepo.getGods()
    _gods.value = gods.successOr(listOf())
  }
}