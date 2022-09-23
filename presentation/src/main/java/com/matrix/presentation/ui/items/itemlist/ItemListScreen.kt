package com.matrix.presentation.ui.items.itemlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix.domain.models.ItemInformation
import com.matrix.presentation.ui.ItemList
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader

@OptIn(ExperimentalLifecycleComposeApi::class)
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
      is ItemListUiState.Error -> ErrorText(itemListUiState.exception.toString())
      is ItemListUiState.Success -> {
        ItemList(
          uiState = itemListUiState,
          itemClicked = itemClicked,
          updateAppliedItemFilters = itemListViewModel::updateAppliedFilters,
          updateSearchText = itemListViewModel::updateSearchText,
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}