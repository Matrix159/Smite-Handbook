package com.matrix.materializedsmite.viewmodels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.api.models.GodInformation
import com.matrix.api.models.Item
import com.matrix.materializedsmite.SmiteApplication
import com.matrix.materializedsmite.cache.Cache
import com.matrix.materializedsmite.repositories.smite.SmiteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject

class ItemListCache(private val sharedPreferences: SharedPreferences) :
  Cache<String, List<Item>> {
  override suspend fun getAsync(key: String): List<Item> {
    return withContext(Dispatchers.IO) {
      sharedPreferences.getString(key, null)?.let {
        Json.decodeFromString<List<Item>>(it)
      } ?: listOf()
    }
  }

  override suspend fun setAsync(key: String, value: List<Item>) {
    return withContext(Dispatchers.IO) {
      sharedPreferences.edit().let {
        it.putString(key, Json.encodeToString(value))
        it.apply()
      }
    }
  }
}

const val ITEM_LIST_CACHE_KEY = "item_list_cache"
const val ITEM_STATE = "ItemViewModel_Item"

@HiltViewModel
class ItemViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository,
  private val savedStateHandle: SavedStateHandle,
) : ViewModel(), CanError {

  private val itemListCache = ItemListCache(SmiteApplication.instance.getSharedPreferences(ITEM_LIST_CACHE_KEY, Context.MODE_PRIVATE))
  private val _items = MutableStateFlow<List<Item>>(listOf())
  val items: StateFlow<List<Item>> = _items

  private val _selectedItem = MutableStateFlow<Item?>(savedStateHandle[ITEM_STATE])
  val selectedItem: StateFlow<Item?> = _selectedItem

  init {
    Timber.d("Init of ItemViewModel")
    viewModelScope.launch(Dispatchers.IO) {
      try {
        val itemList = itemListCache.getAsync(ITEM_LIST_CACHE_KEY).ifEmpty {
          val newResults = smiteRepo.getItems()
          itemListCache.setAsync(ITEM_LIST_CACHE_KEY, newResults)
          newResults
        }
        _items.value = itemList
        error = null
      } catch (ex: Exception) {
        error = ex
        Timber.e(ex.toString())
      }
    }
  }

  fun setItem(item: Item) {
    _selectedItem.value = item
    savedStateHandle[ITEM_STATE] = item
  }

  override var error: Exception? = null

}