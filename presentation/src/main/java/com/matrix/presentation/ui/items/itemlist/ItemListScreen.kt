package com.matrix.presentation.ui.items.itemlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix.presentation.R
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader
import com.matrix.shared.data.model.items.ItemInformation

@Composable
fun ItemListScreen(
  itemListViewModel: ItemListViewModel,
  itemClicked: (itemInformation: ItemInformation) -> Unit,
  modifier: Modifier = Modifier,
) {
  val uiState by itemListViewModel.uiState.collectAsStateWithLifecycle()

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    when (val itemListUiState = uiState) {
      ItemListUiState.Loading -> Loader()
      is ItemListUiState.Error -> ErrorText(stringResource(R.string.unknown_error))
      is ItemListUiState.Success -> {
        FilterableItemList(
          items = itemListUiState.items,
          itemSelected = itemClicked,
          appliedFilters = itemListUiState.appliedItemFilters,
          updateAppliedFilters = itemListViewModel::updateAppliedFilters,
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}