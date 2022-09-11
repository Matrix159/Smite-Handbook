package com.matrix.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.matrix.presentation.NavigationRoutes
import com.matrix.presentation.models.LoadingState
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader
import com.matrix.presentation.ui.components.filters.FilterModal
import com.matrix.presentation.ui.components.filters.ItemFilters
import com.matrix.presentation.ui.components.filters.SearchPanel
import com.matrix.presentation.viewmodels.ItemViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemList(
  viewModel: ItemViewModel,
  navController: NavController,
  modifier: Modifier = Modifier,
) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
  ) {
    when (viewModel.uiState.loadingState) {
      LoadingState.LOADING -> Loader()
      LoadingState.ERROR -> ErrorText(viewModel.uiState.errorMessages.joinToString(", "))
      LoadingState.DONE -> {
        // Wrapped in ItemFilterModal for a bottom sheet modal for filters
        FilterModal(
          sheetContent = {
            ItemFilters(
              appliedItemFilters = viewModel.filters,
              filtersChanged = viewModel::updateAppliedFilters,
              modifier = Modifier.padding(16.dp),
            )
          }
        ) { bottomSheetState ->
          val focusManager = LocalFocusManager.current
          Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
              .fillMaxSize()
              .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
              ) {
                focusManager.clearFocus()
              }
          ) {
            val coroutineScope = rememberCoroutineScope()
            SearchPanel(
              searchText = viewModel.filters.searchText,
              searchLabel = "Search for an item",
              searchTextChanged = viewModel::updateSearchText,
              filterIconTap = {
                coroutineScope.launch {
                  bottomSheetState.show()
                }
              },
              modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
            )
            if (viewModel.visibleItems.isNotEmpty()) {
              LazyVerticalGrid(
                columns = GridCells.Fixed(3)
              ) {
                items(items = viewModel.visibleItems, key = { it.itemID }) { item ->
                  Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                      .requiredSize(128.dp)
                      .padding(8.dp)
                      .clip(MaterialTheme.shapes.extraLarge)
                      .border(
                        1.dp,
                        MaterialTheme.colorScheme.outline,
                        MaterialTheme.shapes.extraLarge
                      )
                      .clickable {
                        focusManager.clearFocus()
                        viewModel.setItem(item)
                        navController.navigate(NavigationRoutes.ItemDetails)
                      }
                  ) {
                    AsyncImage(
                      model = item.itemIconURL,
                      contentDescription = item.deviceName,
                      contentScale = ContentScale.FillWidth,
                      alignment = Alignment.Center,
                      modifier = Modifier
                        //.height(80.dp)
                        .matchParentSize()
                    )
                    Box(
                      modifier = Modifier
                        .matchParentSize()
                        .background(
                          brush = Brush.verticalGradient(
                            0F to Color.Transparent,
                            .5F to Color(0x40000000),
                            .75f to Color(0x80000000),
                            1f to Color(0xFF000000)
                          )
                        )
                    )
                    Text(
                      text = item.deviceName,
                      fontWeight = FontWeight.Bold,
                      fontSize = 14.sp,
                      color = Color.White,
                      textAlign = TextAlign.Center,
                      modifier = Modifier.padding(8.dp)
                    )
                  }
                }
              }
            } else {
              Text("No results found.", style = MaterialTheme.typography.bodyLarge)
            }
          }
//          AnimatedVisibility(selectedItem != null) {
//            selectedItem?.let {
//              ItemDetails(
//                selectedItem,
//                viewModel.uiState.baseItemTreeNodes,
//                Modifier
//                  .background(MaterialTheme.colorScheme.background)
//                  .fillMaxSize()
//                  .clickable { viewModel.setItem(null) }
//                  .padding(16.dp)
//              ) {
//                viewModel.setItem(it)
//              }
//            }
//          }
        }
      }
    }
  }
}
