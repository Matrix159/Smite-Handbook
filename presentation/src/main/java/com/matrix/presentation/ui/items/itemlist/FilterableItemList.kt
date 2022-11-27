package com.matrix.presentation.ui.items.itemlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.matrix.shared.data.models.ItemInformation
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedItemFilters
import com.matrix.presentation.models.filters.ItemTier
import com.matrix.presentation.models.filters.ItemType
import com.matrix.presentation.ui.components.filters.FilterModal
import com.matrix.presentation.ui.components.filters.ItemFilters
import com.matrix.presentation.ui.components.filters.SearchPanel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterableItemList(
  items: List<ItemInformation>,
  itemClicked: (itemInformation: ItemInformation) -> Unit,
  modifier: Modifier = Modifier,
) {

  var appliedFilters by remember { mutableStateOf(AppliedItemFilters()) }
  fun updateAppliedFilters(newFilters: AppliedItemFilters) {
    appliedFilters = newFilters
  }
  fun updateSearchText(text: String) {
    appliedFilters = appliedFilters.copy(searchText = text)
  }

  val shownItems = remember(appliedFilters) {
    items.filter { filterItem(appliedFilters, it) }.sortedBy { it.deviceName }
  }

  FilterModal(
    sheetContent = {
      ItemFilters(
        appliedItemFilters = appliedFilters,
        filtersChanged = ::updateAppliedFilters,
        modifier = Modifier.padding(16.dp),
      )
    }
  ) { bottomSheetState ->
    val focusManager = LocalFocusManager.current
    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = modifier
        .clickable(
          indication = null,
          interactionSource = remember { MutableInteractionSource() }
        ) {
          focusManager.clearFocus()
        }
    ) {
      val coroutineScope = rememberCoroutineScope()
      SearchPanel(
        searchText = appliedFilters.searchText,
        searchLabel = stringResource(R.string.search_for_item),
        searchTextChanged = ::updateSearchText,
        filterIconTap = {
          coroutineScope.launch {
            bottomSheetState.show()
          }
        },
        modifier = Modifier
          .padding(16.dp)
          .fillMaxWidth()
      )
      if (shownItems.isNotEmpty()) {
        ItemList(items = shownItems, itemClicked = itemClicked)
      } else {
        Text(
          text = stringResource(R.string.no_results_found),
          style = MaterialTheme.typography.bodyLarge
        )
      }
    }
  }
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
