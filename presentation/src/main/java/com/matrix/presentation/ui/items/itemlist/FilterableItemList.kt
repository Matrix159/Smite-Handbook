package com.matrix.presentation.ui.items.itemlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.matrix.domain.models.ItemInformation
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedItemFilters
import com.matrix.presentation.ui.components.filters.FilterModal
import com.matrix.presentation.ui.components.filters.ItemFilters
import com.matrix.presentation.ui.components.filters.SearchPanel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterableItemList(
  uiState: ItemListUiState.Success,
  itemClicked: (itemInformation: ItemInformation) -> Unit,
  updateAppliedItemFilters: (itemFilters: AppliedItemFilters) -> Unit,
  updateSearchText: (text: String) -> Unit,
  modifier: Modifier = Modifier,
) {
  FilterModal(
    sheetContent = {
      ItemFilters(
        appliedItemFilters = uiState.filters,
        filtersChanged = updateAppliedItemFilters,
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
        searchText = uiState.filters.searchText,
        searchLabel = stringResource(R.string.search_for_item),
        searchTextChanged = updateSearchText,
        filterIconTap = {
          coroutineScope.launch {
            bottomSheetState.show()
          }
        },
        modifier = Modifier
          .padding(16.dp)
          .fillMaxWidth()
      )
      ItemList(items = uiState.items, itemClicked = itemClicked)
    }
  }
}
