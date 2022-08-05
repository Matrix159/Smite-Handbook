package com.matrix.presentation.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.Item
import com.matrix.presentation.cache.Cache
import com.matrix.presentation.utils.ItemNode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
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
const val SELECTED_ITEM_STATE = "ItemViewModel_SelectedViewModel"

enum class LoadingState {
  LOADING,
  ERROR,
  DONE
}

data class ItemUiState(
  val loadingState: LoadingState = LoadingState.LOADING,
  val items: List<Item> = listOf(),
  val baseItemTreeNodes: List<ItemNode> = listOf(),
  val selectedItem: Item? = null,
  val errorMessages: List<String> = listOf()
)

@HiltViewModel
class ItemViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository,
  private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

//  private val itemListCache = ItemListCache(
//    SmiteApplication.instance.getSharedPreferences(
//      ITEM_LIST_CACHE_KEY,
//      Context.MODE_PRIVATE
//    )
//  )

  var uiState by mutableStateOf(
    ItemUiState(
    selectedItem = savedStateHandle[SELECTED_ITEM_STATE]
    )
  )
    private set

  private var _stateJob: Job? = null

  suspend fun loadState() {
    Timber.d("loadState")
    var newState = uiState.copy()
    _stateJob?.cancel()
    withContext(Dispatchers.IO) {

    }
    _stateJob = viewModelScope.launch {
      try {
        val itemList = withContext(Dispatchers.IO) {
          smiteRepo.getItems()
        }
        newState = newState.copy(items = itemList.filter { it.activeFlag == "y" })
      } catch (ex: Exception) {
        newState = newState.copy(errorMessages = listOf(ex.toString()))
        Timber.e(ex.toString())
      }

      // Set up the item tree's via a node structure
      //_itemIdMap.value = itemList.associateBy { item -> item.itemID }
      val itemsGroupedByTier = newState.items.groupBy { it.itemTier }
      val baseNodes = mutableListOf<ItemNode>()
      newState.items.filter { item -> item.itemTier == 1L }.forEach {
        baseNodes.add(ItemNode(it))
      }
      baseNodes.forEach { node ->
        node.findChildren(itemsGroupedByTier)
      }
      newState = newState.copy(baseItemTreeNodes = baseNodes, loadingState = LoadingState.DONE)

      // Finalize changes to the new state
      uiState = newState
      Timber.d("End loadState")
    }
  }

  fun setItem(item: Item?) {
    uiState = uiState.copy(selectedItem = item)
    // TODO: The item class needs to be parcelable
    //savedStateHandle[SELECTED_ITEM_STATE] = item
  }
}
