package com.matrix.presentation.ui.builds.itemselection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader
import com.matrix.presentation.ui.items.itemlist.FilterableItemList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSelectionScreen(
  viewModel: ItemSelectionViewModel,
  done: (List<Long>) -> Unit,
  modifier: Modifier = Modifier,
) {
  val _uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val focusManager = LocalFocusManager.current

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = modifier
  ) {
    when (val uiState = _uiState) {
      is ItemSelectionUiState.Error -> ErrorText(uiState.exception)
      is ItemSelectionUiState.Loading -> Loader()
      is ItemSelectionUiState.Success -> {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .clickable(
              indication = null,
              interactionSource = remember { MutableInteractionSource() }
            ) {
              focusManager.clearFocus()
            }
        ) {
          Column {
            if (uiState.selectedItems.isNotEmpty()) {
              Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                  .background(color = MaterialTheme.colorScheme.secondaryContainer)
                  //.height(88.dp)
                  .padding(4.dp)
                  .fillMaxWidth()
              ) {
                for (item in uiState.selectedItems) {
                  Box(modifier = Modifier.weight(1f, false)) {
                    AsyncImage(
                      model = item.itemIconURL,
                      contentDescription = item.deviceName,
                      modifier = Modifier
                        .padding(8.dp)
                        .size(48.dp)
                    )
                    Badge(
                      modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable { viewModel.removeItem(item) }
                    ) {
                      Icon(
                        Icons.Default.Close,
                        contentDescription = "remove ${item.itemDescription}",
                      )
                    }
                  }
                }
              }
            }
            FilterableItemList(
              items = uiState.items,
              itemSelected = { item ->
                // Builds should only have 6 total items and no duplicates
                if (uiState.selectedItems.size < 6 && !uiState.selectedItems.contains(item)) {
                  viewModel.addItem(item)
                }
              },
              appliedFilters = uiState.appliedItemFilters,
              updateAppliedFilters = viewModel::updateAppliedFilters,
              modifier = Modifier.weight(1f)
            )
          }
          FloatingActionButton(
            onClick = {
              done(uiState.selectedItems.map { it.itemID })
            },
            modifier = Modifier
              .align(Alignment.BottomEnd)
              .padding(16.dp)
          ) {
            Icon(Icons.Default.Done, contentDescription = null)
          }
        }

      }
    }
  }

}