package com.matrix.presentation.viewmodels

import android.content.SharedPreferences
import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.Item
import com.matrix.domain.usecases.GetLatestItemsUseCase
import com.matrix.presentation.cache.Cache
import com.matrix.presentation.models.LoadingState
import com.matrix.presentation.utils.ItemNode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.Serializable
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

const val SAVED_STATE_KEY = "ItemViewModel_uiState"

@Parcelize
data class ItemUiState(
  val loadingState: LoadingState = LoadingState.LOADING,
  val items: @RawValue List<Item> = listOf(),
  val baseItemTreeNodes: @RawValue List<ItemNode> = listOf(),
  val selectedItem: @RawValue Item? = null,
  val errorMessages: List<String> = listOf()
) : Parcelable


@HiltViewModel
class ItemViewModel @Inject constructor(
  private val getLatestItemsUseCase: GetLatestItemsUseCase,
  private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

  var uiState by mutableStateOf(
    // TODO Need to make presentation specific models that can all be parcelable, to avoid errors
    // savedStateHandle[SAVED_STATE_KEY] ?: ItemUiState()
    ItemUiState()
  )
    private set

  init {
    Timber.d("loadState")
    var newState = uiState.copy()
    viewModelScope.launch {
      try {
        val itemList = withContext(Dispatchers.IO) {
          getLatestItemsUseCase()
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
      newState =
        newState.copy(baseItemTreeNodes = baseNodes, loadingState = LoadingState.DONE)

      // Finalize changes to the new state
      uiState = newState
      //savedStateHandle[SAVED_STATE_KEY] = uiState
      Timber.d("End loadState")
    }
  }

  fun setItem(item: Item?) {
    uiState = uiState.copy(selectedItem = item)
    //savedStateHandle[SAVED_STATE_KEY] = uiState
  }
}
