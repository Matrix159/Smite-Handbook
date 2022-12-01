package com.matrix.presentation.ui.items.itemdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader
import com.matrix.shared.data.models.ItemInformation

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ItemDetailsScreen(
  itemDetailsViewModel: ItemDetailsViewModel,
  itemClicked: (itemInformation: ItemInformation) -> Unit,
  modifier: Modifier = Modifier,
) {

  val uiState by itemDetailsViewModel.uiState.collectAsStateWithLifecycle()
  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
      .statusBarsPadding()
      .verticalScroll(rememberScrollState())
      .fillMaxSize()
  ) {
    when (val itemDetailUiState = uiState) {
      ItemDetailUiState.Loading -> Loader()
      is ItemDetailUiState.Error -> ErrorText(itemDetailUiState.exception.toString())
      is ItemDetailUiState.Success -> {
        ItemDetails(
          uiState = itemDetailUiState,
          itemClicked = itemClicked,
          modifier = Modifier.align(Alignment.TopStart)
        )
      }
    }
  }
}