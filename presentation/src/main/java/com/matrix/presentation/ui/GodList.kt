package com.matrix.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.matrix.domain.models.GodInformation
import com.matrix.presentation.models.LoadingState
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader
import com.matrix.presentation.ui.components.filters.FilterModal
import com.matrix.presentation.ui.components.filters.GodFilters
import com.matrix.presentation.ui.components.filters.SearchPanel
import com.matrix.presentation.viewmodels.GodViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GodList(
  viewModel: GodViewModel,
  modifier: Modifier = Modifier,
  godClicked: (godInfo: GodInformation) -> Unit,
) {
  val godListUiState = viewModel.godListUiState

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    when (godListUiState.loadingState) {
      LoadingState.LOADING -> Loader()
      LoadingState.ERROR -> ErrorText(godListUiState.errors.joinToString(","))
      LoadingState.DONE -> {
        FilterModal(
          sheetContent = {
            GodFilters(
              appliedFilters = viewModel.filters,
              filtersChanged = viewModel::updateAppliedFilters,
              modifier = Modifier.padding(16.dp),
            )
          }
        ) { bottomSheetState ->
          Column {
            val coroutineScope = rememberCoroutineScope()
            SearchPanel(
              searchText = viewModel.filters.searchText,
              searchLabel = "Search for a god",
              searchTextChanged = viewModel::updateSearchText,
              filterIconTap = {
                coroutineScope.launch {
                  bottomSheetState.show()
                }
              },
              modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            LazyVerticalGrid(
              columns = GridCells.Fixed(3)
            ) {
              items(items = viewModel.visibleItems, key = { it.id }) { god ->
                Box(
                  contentAlignment = Alignment.BottomCenter,
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .border(
                      1.dp,
                      MaterialTheme.colorScheme.outline,
                      MaterialTheme.shapes.extraLarge
                    )
                    .clickable { godClicked(god) }
                ) {
                  AsyncImage(
                    model = god.godCardURL,
                    contentDescription = god.name,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                      .height(180.dp)
                      .fillMaxWidth()
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
                    text = god.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}