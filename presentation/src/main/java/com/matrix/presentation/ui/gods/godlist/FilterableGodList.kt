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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matrix159.shared.data.models.GodInformation
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.presentation.models.filters.Pantheon
import com.matrix.presentation.models.filters.Role
import com.matrix.presentation.ui.components.filters.FilterModal
import com.matrix.presentation.ui.components.filters.SearchPanel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterableGodList(
  gods: List<GodInformation>,
  godSelected: (godInfo: GodInformation) -> Unit,
  modifier: Modifier = Modifier,
) {

  var appliedFilters by remember { mutableStateOf(AppliedGodFilters()) }
  fun updateAppliedFilters(newFilters: AppliedGodFilters) {
    appliedFilters = newFilters
  }
  fun updateSearchText(text: String) {
    appliedFilters = appliedFilters.copy(searchText = text)
  }

  val shownGods = remember(appliedFilters) {
    gods.filter { filterGod(appliedFilters, it) }.sortedBy { it.name }
  }

  FilterModal(
    sheetContent = {
      GodFilters(
        appliedFilters = appliedFilters,
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
        searchLabel = stringResource(R.string.search_for_god),
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
      if (shownGods.isNotEmpty()) {
        GodList(gods = shownGods, godClicked = godSelected)
      } else {
        Text(
          text = stringResource(R.string.no_results_found),
          style = MaterialTheme.typography.bodyLarge
        )
      }
    }
  }
}

private fun filterGod(filters: AppliedGodFilters, god: GodInformation): Boolean {
  return (if (filters.searchText.isNotBlank())
    god.name.contains(filters.searchText, true)
  else true) &&
    // Role
    (if (filters.roles.isNotEmpty()) filters.roles.contains(
      Role.values().first { it.roleName == god.roles }) else true) &&
    // Pantheon
    (if (filters.pantheons.isNotEmpty()) filters.pantheons.contains(
      Pantheon.values().first { it.pantheonName == god.pantheon }) else true)
}


@Preview
@Composable
fun FilterableGodListPreview() {
//  FilterableGodList(
//    uiState =,
//    godSelected =,
//    updateAppliedGodFilters =,
//    updateSearchText =
//  )
}