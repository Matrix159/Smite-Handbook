package com.matrix.materializedsmite.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
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
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ITEMS_STATE = "ItemViewModel_Items"
const val ITEM_STATE = "ItemViewModel_Item"

@HiltViewModel
class ItemViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository,
  private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private val _items = MutableStateFlow<List<Item>>(savedStateHandle[ITEMS_STATE] ?: listOf())
  val items: StateFlow<List<Item>> = _items

  private val _selectedItem = MutableStateFlow<Item?>(savedStateHandle[ITEM_STATE])
  val selectedItem: StateFlow<Item?> = _selectedItem

  init {
    viewModelScope.launch(Dispatchers.IO) {
      if (_items.value.isEmpty()) {
        try {
          _items.value = smiteRepo.getItems()
          savedStateHandle[ITEMS_STATE] = _items.value
          Log.i("items were loaded", "here")
        } catch (ex: Exception) {
          Log.e("ItemViewModel", ex.toString())
        }
      }
    }
  }

  fun setItem(item: Item) {
    _selectedItem.value = item
    savedStateHandle[ITEM_STATE] = item
  }
}