package com.matrix.presentation.ui.itemdetails.filters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedFilters


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
        selected = appliedFilters.consumable,
        onClick = { filtersChanged(appliedFilters.copy(consumable = !appliedFilters.consumable)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }) {
        Text("Consumable")
      }
      FilterChip(
        selected = appliedFilters.item,
        onClick = { filtersChanged(appliedFilters.copy(item = !appliedFilters.item)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }) {
        Text("Item")
      }
      FilterChip(
        selected = appliedFilters.active,
        onClick = { filtersChanged(appliedFilters.copy(active = !appliedFilters.active)) },
        selectedIcon = { Icon(Icons.Filled.Done, stringResource(R.string.applied_filter)) }) {
        Text("Active")
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
