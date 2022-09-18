package com.matrix.presentation.ui.items

import android.os.Parcelable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.ItemInformation
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
  val itemInformations: @RawValue List<ItemInformation> = listOf(),
  val baseItemTreeNodes: @RawValue List<ItemNode> = listOf(),
  val selectedItemInformation: @RawValue ItemInformation? = null,
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
    uiState.itemInformations.filter { item ->
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
        newState = newState.copy(itemInformations = itemList.filter { it.activeFlag })
      } catch (ex: Exception) {
        newState = newState.copy(errorMessages = listOf(ex.toString()))
        Timber.e(ex.toString())
      }

      // Set up the item tree's via a node structure
      //_itemIdMap.value = itemList.associateBy { item -> item.itemID }
      val itemsGroupedByTier = newState.itemInformations.groupBy { it.itemTier }
      val baseNodes = mutableListOf<ItemNode>()
      newState.itemInformations.filter { item -> item.itemTier == 1 }.forEach {
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

  fun setItem(itemInformation: ItemInformation?) {
    uiState = uiState.copy(selectedItemInformation = itemInformation)
    //savedStateHandle[SAVED_STATE_KEY] = uiState
  }

  private fun filterItem(filters: AppliedItemFilters, itemInformation: ItemInformation): Boolean {
    return itemInformation.activeFlag &&
      (if (filters.searchText.isNotBlank())
        itemInformation.deviceName.contains(filters.searchText, true)
      else true) &&
      // Type
      (if (filters.type != null)
        filters.type == ItemType.Consumable && itemInformation.type == "Consumable" ||
          filters.type == ItemType.Item && itemInformation.type == "Item" ||
          filters.type == ItemType.Active && itemInformation.type == "Active"
      else true) &&
      // Tier
      (if (filters.tier != null)
        filters.tier == ItemTier.One && itemInformation.itemTier == 1 ||
          filters.tier == ItemTier.Two && itemInformation.itemTier == 2 ||
          filters.tier == ItemTier.Three && itemInformation.itemTier == 3 ||
          filters.tier == ItemTier.Four && itemInformation.itemTier == 4
      else true) &&
      // Offense
      (if (filters.magicalPower)
        itemInformation.itemDescription.menuItems.any { it.description == "Magical Power" }
      else true) &&
      (if (filters.magicalLifeSteal)
        itemInformation.itemDescription.menuItems.any { it.description == "Magical Lifesteal" }
      else true) &&
      (if (filters.magicalFlatPen)
        itemInformation.itemDescription.menuItems.any {
          it.description == "Magical Penetration" && !it.value.contains(
            "%"
          )
        }
      else true) &&
      (if (filters.magicalPercentPen)
        itemInformation.itemDescription.menuItems.any {
          it.description == "Magical Penetration" && it.value.contains(
            "%"
          )
        }
      else true) &&
      (if (filters.physicalPower)
        itemInformation.itemDescription.menuItems.any { it.description == "Physical Power" }
      else true) &&
      (if (filters.physicalLifeSteal)
        itemInformation.itemDescription.menuItems.any { it.description == "Physical Lifesteal" }
      else true) &&
      (if (filters.physicalFlatPen)
        itemInformation.itemDescription.menuItems.any {
          it.description == "Physical Penetration" && !it.value.contains(
            "%"
          )
        }
      else true) &&
      (if (filters.physicalPercentPen)
        itemInformation.itemDescription.menuItems.any {
          it.description == "Physical Penetration" && it.value.contains(
            "%"
          )
        }
      else true) &&
      (if (filters.attackSpeed)
        itemInformation.itemDescription.menuItems.any { it.description == "Attack Speed" }
      else true) &&
      (if (filters.critChance)
        itemInformation.itemDescription.menuItems.any { it.description == "Critical Strike Chance" }
      else true) &&
      (if (filters.basicAttackDamage)
        itemInformation.itemDescription.menuItems.any { it.description == "Basic Attack Damage" }
      else true) &&
      // Defense
      (if (filters.magicalProtection)
        itemInformation.itemDescription.menuItems.any { it.description == "Magical Protection" }
      else true) &&
      (if (filters.physicalProtection)
        itemInformation.itemDescription.menuItems.any { it.description == "Physical Protection" }
      else true) &&
      (if (filters.health)
        itemInformation.itemDescription.menuItems.any { it.description == "Health" }
      else true) &&
      (if (filters.hp5)
        itemInformation.itemDescription.menuItems.any { it.description == "HP5" }
      else true) &&
      (if (filters.ccr)
        itemInformation.itemDescription.menuItems.any { it.description == "Crowd Control Reduction" }
      else true) &&
      // Utility
      (if (filters.cdr)
        itemInformation.itemDescription.menuItems.any { it.description == "Cooldown Reduction" }
      else true) &&
      (if (filters.mana)
        itemInformation.itemDescription.menuItems.any { it.description == "Mana" }
      else true) &&
      (if (filters.mp5)
        itemInformation.itemDescription.menuItems.any { it.description == "MP5" }
      else true) &&
      (if (filters.movementSpeed)
        itemInformation.itemDescription.menuItems.any { it.description == "Movement Speed" }
      else true)
  }
}

