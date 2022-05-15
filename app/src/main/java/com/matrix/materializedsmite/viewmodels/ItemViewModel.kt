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
class ItemViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository
) : ViewModel() {

  private val _items = MutableStateFlow<List<Item>>(listOf())
  val items: StateFlow<List<Item>> = _items

  private val _selectedItem = MutableStateFlow<Item?>(null)
  val selectedItem: StateFlow<Item?> = _selectedItem

  init {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        _items.value = smiteRepo.getItems()
      } catch (ex: Exception) {
        Log.e("ItemViewModel", ex.toString())
      }
    }
  }

  fun setItem(item: Item) {
    _selectedItem.value = item
  }
}