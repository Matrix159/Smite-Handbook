package com.matrix.presentation.viewmodels

import android.os.Parcelable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.Item
import com.matrix.domain.usecases.GetLatestItemsUseCase
import com.matrix.presentation.models.LoadingState
import com.matrix.presentation.models.filters.AppliedItemFilters
import com.matrix.presentation.models.filters.ItemTier
import com.matrix.presentation.models.filters.ItemType
import com.matrix.presentation.utils.ItemNode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import timber.log.Timber
import javax.inject.Inject

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

  var filters by mutableStateOf(
    AppliedItemFilters()
  )
    private set

  val visibleItems by derivedStateOf {
    uiState.items.filter { item ->
      filterItem(filters, item)
    }.sortedBy { it.deviceName }
  }

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
      newState.items.filter { item -> item.itemTier == 1 }.forEach {
        baseNodes.add(ItemNode(it))
      }
      baseNodes.forEach { node ->
        node.findChildren(itemsGroupedByTier)
      }
      newState = newState.copy(baseItemTreeNodes = baseNodes, loadingState = LoadingState.DONE)

      // Finalize changes to the new state
      uiState = newState
      //savedStateHandle[SAVED_STATE_KEY] = uiState
      Timber.d("End loadState")
    }
  }

  fun updateAppliedFilters(newFilters: AppliedItemFilters) {
    filters = newFilters.copy()
  }

  fun updateSearchText(text: String) {
    filters = filters.copy(searchText = text)
  }

  fun setItem(item: Item?) {
    uiState = uiState.copy(selectedItem = item)
    //savedStateHandle[SAVED_STATE_KEY] = uiState
  }

  private fun filterItem(filters: AppliedItemFilters, item: Item): Boolean {
    return item.activeFlag == "y" &&
      (if (filters.searchText.isNotBlank())
        item.deviceName.contains(filters.searchText, true)
      else true) &&
      // Type
      (if (filters.type != null)
        filters.type == ItemType.Consumable && item.type == "Consumable" ||
          filters.type == ItemType.Item && item.type == "Item" ||
          filters.type == ItemType.Active && item.type == "Active"
      else true) &&
      // Tier
      (if (filters.tier != null)
        filters.tier == ItemTier.One && item.itemTier == 1 ||
          filters.tier == ItemTier.Two && item.itemTier == 2 ||
          filters.tier == ItemTier.Three && item.itemTier == 3 ||
          filters.tier == ItemTier.Four && item.itemTier == 4
      else true) &&
      // Offense
      (if (filters.magicalPower)
        item.itemDescription.menuItems.any { it.description == "Magical Power" }
      else true) &&
      (if (filters.magicalLifeSteal)
        item.itemDescription.menuItems.any { it.description == "Magical Lifesteal" }
      else true) &&
      (if (filters.magicalFlatPen)
        item.itemDescription.menuItems.any {
          it.description == "Magical Penetration" && !it.value.contains(
            "%"
          )
        }
      else true) &&
      (if (filters.magicalPercentPen)
        item.itemDescription.menuItems.any {
          it.description == "Magical Penetration" && it.value.contains(
            "%"
          )
        }
      else true) &&
      (if (filters.physicalPower)
        item.itemDescription.menuItems.any { it.description == "Physical Power" }
      else true) &&
      (if (filters.physicalLifeSteal)
        item.itemDescription.menuItems.any { it.description == "Physical Lifesteal" }
      else true) &&
      (if (filters.physicalFlatPen)
        item.itemDescription.menuItems.any {
          it.description == "Physical Penetration" && !it.value.contains(
            "%"
          )
        }
      else true) &&
      (if (filters.physicalPercentPen)
        item.itemDescription.menuItems.any {
          it.description == "Physical Penetration" && it.value.contains(
            "%"
          )
        }
      else true) &&
      (if (filters.attackSpeed)
        item.itemDescription.menuItems.any { it.description == "Attack Speed" }
      else true) &&
      (if (filters.critChance)
        item.itemDescription.menuItems.any { it.description == "Critical Strike Chance" }
      else true) &&
      (if (filters.basicAttackDamage)
        item.itemDescription.menuItems.any { it.description == "Basic Attack Damage" }
      else true) &&
      // Defense
      (if (filters.magicalProtection)
        item.itemDescription.menuItems.any { it.description == "Magical Protection" }
      else true) &&
      (if (filters.physicalProtection)
        item.itemDescription.menuItems.any { it.description == "Physical Protection" }
      else true) &&
      (if (filters.health)
        item.itemDescription.menuItems.any { it.description == "Health" }
      else true) &&
      (if (filters.hp5)
        item.itemDescription.menuItems.any { it.description == "HP5" }
      else true) &&
      (if (filters.ccr)
        item.itemDescription.menuItems.any { it.description == "Crowd Control Reduction" }
      else true) &&
      // Utility
      (if (filters.cdr)
        item.itemDescription.menuItems.any { it.description == "Cooldown Reduction" }
      else true) &&
      (if (filters.mana)
        item.itemDescription.menuItems.any { it.description == "Mana" }
      else true) &&
      (if (filters.mp5)
        item.itemDescription.menuItems.any { it.description == "MP5" }
      else true) &&
      (if (filters.movementSpeed)
        item.itemDescription.menuItems.any { it.description == "Movement Speed" }
      else true)
  }
}

