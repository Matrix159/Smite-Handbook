package com.matrix.presentation.ui.items.itemdetails

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
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ItemDetailsScreen(
  itemDetailsViewModel: ItemDetailsViewModel,
  itemClicked: (itemInformation: ItemInformation) -> Unit,
  modifier: Modifier = Modifier
) {

  val uiState by itemDetailsViewModel.uiState.collectAsStateWithLifecycle()
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    when (val itemDetailUiState = uiState) {
      ItemDetailUiState.Loading -> Loader()
      is ItemDetailUiState.Error -> ErrorText(itemDetailUiState.exception.toString())
      is ItemDetailUiState.Success -> {
        ItemDetails(
          uiState = itemDetailUiState,
          itemClicked = itemClicked,
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}