package com.matrix.materializedsmite.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.api.models.Item
import com.matrix.materializedsmite.SmiteApplication
import com.matrix.materializedsmite.cache.Cache
import com.matrix.materializedsmite.repositories.smite.SmiteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
const val ITEM_STATE = "ItemViewModel_Item"

data class ItemUiState(
  val items: List<Item>,
  val itemIdMap: Map<Long, Item>,
  val errorMessages: List<String>
)

@HiltViewModel
class ItemViewModel @Inject constructor(
  private val smiteRepo: SmiteRepository,
  private val savedStateHandle: SavedStateHandle,
) : ViewModel(), CanError {

  private val itemListCache = ItemListCache(
    SmiteApplication.instance.getSharedPreferences(
      ITEM_LIST_CACHE_KEY,
      Context.MODE_PRIVATE
    )
  )
  private val _items = MutableStateFlow<List<Item>>(listOf())
  val items: StateFlow<List<Item>> = _items

  private val _itemIdMap: MutableState<Map<Long, Item>?> = mutableStateOf(null)
  val itemIdMap: State<Map<Long, Item>?> = _itemIdMap

  private val _selectedItem = MutableStateFlow<Item?>(savedStateHandle[ITEM_STATE])
  val selectedItem: StateFlow<Item?> = _selectedItem

  suspend fun loadItems() {
    Timber.d("loadItems in ItemViewModel")
    viewModelScope.launch {
      try {
        val itemList = withContext(Dispatchers.IO) {
          itemListCache.getAsync(ITEM_LIST_CACHE_KEY).ifEmpty {
            val newResults = smiteRepo.getItems()
            itemListCache.setAsync(ITEM_LIST_CACHE_KEY, newResults)
            newResults
          }
        }
        _items.value = itemList.filter { it.activeFlag == "y" }
        error = null
      } catch (ex: Exception) {
        error = ex
        Timber.e(ex.toString())
      }

      // TODO: Experiment here
      items.onEach { itemList ->
        _itemIdMap.value = itemList.associateBy { item -> item.itemID }
        // base nodes
        val itemsGroupedByTier = itemList.groupBy { it.itemTier }
        // Take items grouped by tier and turn each root item into its own map of tiers
        val itemTrees = mutableListOf<Map<Long, List<Item>>>()
        var baseNodes = mutableListOf<ItemNode>()
        itemsGroupedByTier[1]?.forEach { tierOneItem ->
          //itemTrees.add(itemList.filter { it.rootItemID == tierOneItem.itemID }.groupBy { it.itemTier }.toSortedMap())
          baseNodes.add(ItemNode(tierOneItem))
        }

        baseNodes.forEach { node ->
          node.findChildren(itemsGroupedByTier)
        }
        Timber.d(baseNodes.joinToString(", "))
        _itemIdMap.value
      }.collect()
    }
  }

  fun setItem(item: Item?) {
    _selectedItem.value = item
    savedStateHandle[ITEM_STATE] = item
  }

  override var error: Exception? = null
}

class ItemNode(value: Item) {
  var value: Item = value
  var parent: ItemNode? = null

  var children: MutableList<ItemNode> = mutableListOf()

  fun addChild(node: ItemNode) {
    children.add(node)
    node.parent = this
  }

  fun findChildren(itemsGroupedByTier: Map<Long, List<Item>>): ItemNode {
    val currentTier = this.value.itemTier

    if (itemsGroupedByTier.containsKey(currentTier + 1)) {
      itemsGroupedByTier[currentTier + 1]!!.filter { it.childItemID == this.value.itemID }.forEach {
        addChild(ItemNode(it).findChildren(itemsGroupedByTier))
      }
    }
    return this
  }

  /**
   * Should be called on the root node of the tree
   */
  fun itemExistsInThisTree(item: Item): Boolean {
    if (this.parent != null) {
      throw UnsupportedOperationException()
    }
    var itemFound = false
    // Search up
    var parentSearchDone = false
    var nextNode: ItemNode? = null
    while (!parentSearchDone) {
      if (nextNode != null) {
        if (nextNode.value.itemID == item.itemID) {
          itemFound = true
          break
        }
      }
      if (this.parent != null) {
        nextNode = this.parent
      } else {
        parentSearchDone = true
      }
    }

    // Search down through children
    var childrenSearchDone = false
    var nextNode: ItemNode? = null
    while (!childrenSearchDone) {
      if (nextNode != null) {
        if (nextNode.value.itemID == item.itemID) {
          itemFound = true
          break
        }
      }
      if (this.parent != null) {
        nextNode = this.parent
      } else {
        parentSearchDone = true
      }
    }

    return itemFound
  }

  fun dfs(nodes: List<List<Int>>) {
    val visited = BooleanArray(nodes.size) { false }
    helper(nodes, 0, visited)
  }

  fun helper(nodes: List<List<Int>>, node: Int, visited: BooleanArray){
    visited[node] = true
    nodes[node].forEach {
      if (!visited[it]) {
        helper(nodes, it, visited)
      }
    }
  }

  override fun toString(): String {
    return this.value.deviceName + "\n" + this.children.joinToString(", ")
  }
}