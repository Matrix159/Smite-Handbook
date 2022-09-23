package com.matrix.presentation.ui.items.itemlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.domain.models.ItemInformation
import com.matrix.domain.models.Result
import com.matrix.domain.models.asResult
import com.matrix.domain.usecases.GetLatestItemsUseCase
import com.matrix.presentation.models.filters.AppliedItemFilters
import com.matrix.presentation.models.filters.ItemTier
import com.matrix.presentation.models.filters.ItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
  getLatestItemsUseCase: GetLatestItemsUseCase,
) : ViewModel() {

  var uiState: StateFlow<ItemListUiState> = combine(
    getLatestItemsUseCase().asResult(),
    snapshotFlow { filters },
  ) { result: Result<List<ItemInformation>>, filters: AppliedItemFilters ->
    when (result) {
      is Result.Success -> {
        when (result.data.isEmpty()) {
          true -> ItemListUiState.Loading
          false -> ItemListUiState.Success(
            items = result.data.filter { item ->
              filterItem(filters, item)
            }.sortedBy { it.deviceName },
            filters = filters
          )
        }
      }
      is Result.Loading -> {
        ItemListUiState.Loading
      }
      is Result.Error -> {
        ItemListUiState.Error(result.exception)
      }
    }
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = ItemListUiState.Loading
  )

  private var filters by mutableStateOf(
    AppliedItemFilters()
  )

  fun updateAppliedFilters(newFilters: AppliedItemFilters) {
    filters = newFilters.copy()
  }

  fun updateSearchText(text: String) {
    filters = filters.copy(searchText = text)
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

sealed interface ItemListUiState {
  data class Success(val items: List<ItemInformation>, val filters: AppliedItemFilters) :
    ItemListUiState

  data class Error(val exception: Throwable?) : ItemListUiState
  object Loading : ItemListUiState
}

