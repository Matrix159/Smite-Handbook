package com.matrix.presentation.ui.gods.godlist

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.matrix.domain.models.GodInformation
import com.matrix.presentation.R
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.presentation.ui.components.filters.FilterModal
import com.matrix.presentation.ui.components.filters.SearchPanel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GodList(
  uiState: GodListUiState.Success,
  godClicked: (godInfo: GodInformation) -> Unit,
  updateAppliedGodFilters: (filters: AppliedGodFilters) -> Unit,
  updateSearchText: (text: String) -> Unit,
  modifier: Modifier = Modifier,
) {

  FilterModal(
    sheetContent = {
      GodFilters(
        appliedFilters = uiState.filters,
        filtersChanged = updateAppliedGodFilters,
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
        searchText = uiState.filters.searchText,
        searchLabel = stringResource(R.string.search_for_god),
        searchTextChanged = updateSearchText,
        filterIconTap = {
          coroutineScope.launch {
            bottomSheetState.show()
          }
        },
        modifier = Modifier
          .padding(16.dp)
          .fillMaxWidth()
      )
      if (uiState.gods.isNotEmpty()) {
        LazyVerticalGrid(
          columns = GridCells.Fixed(3)
        ) {
          items(items = uiState.gods, key = { it.id }) { god ->
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
      } else {
        Text(
          text = stringResource(R.string.no_results_found),
          style = MaterialTheme.typography.bodyLarge
        )
      }
    }
  }
}