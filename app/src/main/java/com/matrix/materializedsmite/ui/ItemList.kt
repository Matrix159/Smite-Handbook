package com.matrix.materializedsmite.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.matrix.materializedsmite.ui.components.ChipRow
import com.matrix.materializedsmite.ui.components.Loader
import com.matrix.materializedsmite.viewmodels.SmiteViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ItemList(
  smiteViewModel: SmiteViewModel,
) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize()
  ) {
    var searchValue by remember { mutableStateOf("") }
    var selectedTier by remember { mutableStateOf<Int?>(null) }
    val items by smiteViewModel.items.collectAsState()
    val filteredItems = remember(items, searchValue, selectedTier) {
      items
        .filter { item ->
          if (searchValue.isNotBlank()) {
            item.deviceName.contains(searchValue, true) && item.activeFlag == "y"
          } else {
            item.activeFlag == "y"
          }
        }.filter {
            item -> selectedTier?.let { item.itemTier.toInt() == it } ?: true
        }
    }
    OutlinedTextField(
      value = searchValue,
      onValueChange = { searchValue = it },
      label = {
        Text("Search for an item")
      },
      singleLine = true,
      modifier = Modifier.padding(4.dp)
    )
    ChipRow(values = listOf("Tier 1", "Tier 2", "Tier 3"), chipSelected = {
      selectedTier = it + 1
    })
    if (items.isEmpty()) {
      Loader()
    }

    if (filteredItems.isNotEmpty()) {
      LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize()
      ) {
        items(items = filteredItems) { item ->
          Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
              .fillMaxWidth()
              .padding(8.dp)
              .clip(MaterialTheme.shapes.extraLarge)
              .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.extraLarge)
            //.clickable { itemClicked(item) }
          ) {
            AsyncImage(
              model = item.itemIconURL,
              contentDescription = item.deviceName,
              contentScale = ContentScale.FillWidth,
              alignment = Alignment.TopCenter,
              modifier = Modifier
                //.height(80.dp)
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
    }
  }
}