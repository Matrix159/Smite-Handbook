package com.matrix.presentation.ui.gods.godlist

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.matrix.domain.models.GodInformation
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.presentation.ui.components.filters.FilterModal
import com.matrix.presentation.ui.components.filters.SearchPanel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterableGodList(
  uiState: GodListUiState.Success,
  godClicked: (godInfo: GodInformation) -> Unit,
  updateAppliedGodFilters: (filters: AppliedGodFilters) -> Unit,
  updateSearchText: (text: String) -> Unit,
  modifier: Modifier = Modifier,
) {

  FilterModal(
    sheetContent = {
      GodFilters(
        appliedFilters = uiState.filters,
        filtersChanged = updateAppliedGodFilters,
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
        searchLabel = stringResource(R.string.search_for_god),
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
      if (uiState.gods.isNotEmpty()) {
        GodList(gods = uiState.gods, godClicked = godClicked)
      } else {
        Text(
          text = stringResource(R.string.no_results_found),
          style = MaterialTheme.typography.bodyLarge
        )
      }
    }
  }
}