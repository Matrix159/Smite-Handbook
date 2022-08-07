package com.matrix.presentation.ui.itemdetails.filters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedFilters
import com.matrix.presentation.models.filters.ItemTier
import com.matrix.presentation.models.filters.ItemType


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFilterModal(
  modifier: Modifier = Modifier,
  filters: AppliedFilters,
  filtersChanged: (filters: AppliedFilters) -> Unit,
  content: @Composable (bottomSheetState: ModalBottomSheetState) -> Unit,
) {
  val bottomSheetState: ModalBottomSheetState =
    rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
  ModalBottomSheetLayout(sheetState = bottomSheetState,
    sheetBackgroundColor = MaterialTheme.colorScheme.surface,
    sheetContentColor = MaterialTheme.colorScheme.onSurface,
    modifier = modifier,
    sheetContent = {
      ItemFilters(
        appliedFilters = filters,
        filtersChanged = filtersChanged,
        modifier = Modifier.padding(16.dp),
      )
    }) {
    content(bottomSheetState)
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFilters(
  modifier: Modifier = Modifier,
  appliedFilters: AppliedFilters,
  filtersChanged: (filters: AppliedFilters) -> Unit,
) {
  Column(modifier = modifier) {
    FilterGroup(title = "Type") {
      FilterChip(
        selected = appliedFilters.type == ItemType.Consumable,
        onClick = {
          filtersChanged(
            appliedFilters.copy(type = if (appliedFilters.type != ItemType.Consumable) ItemType.Consumable else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Consumable")
      }
      FilterChip(
        selected = appliedFilters.type == ItemType.Item,
        onClick = {
          filtersChanged(
            appliedFilters.copy(type = if (appliedFilters.type != ItemType.Item) ItemType.Item else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Item")
      }
      FilterChip(
        selected = appliedFilters.type == ItemType.Active,
        onClick = {
          filtersChanged(
            appliedFilters.copy(type = if (appliedFilters.type != ItemType.Active) ItemType.Active else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Active")
      }
    }
    FilterGroup(title = "Tier") {
      FilterChip(
        selected = appliedFilters.tier == ItemTier.One,
        onClick = {
          filtersChanged(
            appliedFilters.copy(tier = if (appliedFilters.tier != ItemTier.One) ItemTier.One else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 1")
      }
      FilterChip(
        selected = appliedFilters.tier == ItemTier.Two,
        onClick = {
          filtersChanged(
            appliedFilters.copy(tier = if (appliedFilters.tier != ItemTier.Two) ItemTier.Two else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 2")
      }
      FilterChip(
        selected = appliedFilters.tier == ItemTier.Three,
        onClick = {
          filtersChanged(
            appliedFilters.copy(tier = if (appliedFilters.tier != ItemTier.Three) ItemTier.Three else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 3")
      }
      FilterChip(
        selected = appliedFilters.tier == ItemTier.Four,
        onClick = {
          filtersChanged(
            appliedFilters.copy(tier = if (appliedFilters.tier != ItemTier.Four) ItemTier.Four else null)
          )
        },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Tier 4")
      }
    }
    FilterGroup(title = "Offense") {
      FilterChip(
        selected = appliedFilters.magicalPower,
        onClick = { filtersChanged(appliedFilters.copy(magicalPower = !appliedFilters.magicalPower)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }
      ) {
        Text("Magical Power")
      }
    }
//    ChipRow(values = listOf("Tier 1", "Tier 2", "Tier 3", "Tier 4"), unselectable = true) {
//      selectedTier(it?.let { it + 1 })
//    }
  }
}

@Composable
fun FilterGroup(
  title: String,
  content: @Composable () -> Unit
) {
  Text(style = MaterialTheme.typography.titleMedium, text = title)
  Divider(color = MaterialTheme.colorScheme.onSurface)
  FlowRow(mainAxisSpacing = 8.dp) {
    content()
  }
}
