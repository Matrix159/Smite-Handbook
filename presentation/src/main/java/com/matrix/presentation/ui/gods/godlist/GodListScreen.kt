package com.matrix.presentation.ui.gods.godlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix.domain.models.GodInformation
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun GodListScreen(
  godListViewModel: GodListViewModel,
  godClicked: (godInformation: GodInformation) -> Unit,
  modifier: Modifier = Modifier
) {
  val uiState by godListViewModel.godListUiState.collectAsStateWithLifecycle()

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    when (val godListUiState = uiState) {
      GodListUiState.Loading -> Loader()
      is GodListUiState.Error -> ErrorText(godListUiState.exception.toString())
      is GodListUiState.Success -> {
        GodList(
          uiState = godListUiState,
          godClicked = godClicked,
          updateAppliedGodFilters = godListViewModel::updateAppliedFilters,
          updateSearchText = godListViewModel::updateSearchText
        )
      }
    }
  }

}