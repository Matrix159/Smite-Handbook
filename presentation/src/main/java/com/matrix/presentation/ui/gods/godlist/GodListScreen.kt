package com.matrix.presentation.ui.gods.godlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader
import com.matrix.shared.data.model.gods.GodInformation

@Composable
fun GodListScreen(
  godListViewModel: GodListViewModel,
  godClicked: (godInformation: GodInformation) -> Unit,
  modifier: Modifier = Modifier
) {
  val uiState by godListViewModel.uiState.collectAsStateWithLifecycle()

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    when (val godListUiState = uiState) {
      GodListUiState.Loading -> Loader()
      is GodListUiState.Error -> ErrorText(godListUiState.exception.toString())
      is GodListUiState.Success -> {
        FilterableGodList(
          gods = godListUiState.gods,
          godSelected = godClicked,
          appliedFilters = godListUiState.appliedGodFilters,
          updateAppliedFilters = godListViewModel::updateAppliedFilters
        )
      }
    }
  }
}