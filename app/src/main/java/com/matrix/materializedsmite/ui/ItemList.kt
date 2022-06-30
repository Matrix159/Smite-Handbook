package com.matrix.materializedsmite.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.matrix.materializedsmite.ui.components.ChipRow
import com.matrix.materializedsmite.ui.components.ErrorText
import com.matrix.materializedsmite.ui.components.Loader
import com.matrix.materializedsmite.ui.itemdetails.ItemDetails
import com.matrix.materializedsmite.viewmodels.ItemViewModel

@Composable
fun ItemList(
  itemViewModel: ItemViewModel,
  modifier: Modifier = Modifier,
) {
  LaunchedEffect(Unit) {
    itemViewModel.loadItems()
  }

  Box(contentAlignment = Alignment.Center) {
    val selectedItem by itemViewModel.selectedItem.collectAsState()
    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = modifier
    ) {
      val items by itemViewModel.items.collectAsState()
      var searchValue by rememberSaveable { mutableStateOf("") }
      var selectedTier by rememberSaveable { mutableStateOf<Int?>(null) }
      val filteredItems = remember(items, searchValue, selectedTier) {
        items
          .filter { item ->
            if (searchValue.isNotBlank()) {
              item.deviceName.contains(searchValue, true) && item.activeFlag == "y"
            } else {
              item.activeFlag == "y"
            }
          }.filter { item ->
            selectedTier?.let { item.itemTier.toInt() == it } ?: true
          }
      }
      val focusManager = LocalFocusManager.current
      OutlinedTextField(
        value = searchValue,
        onValueChange = { searchValue = it },
        label = {
          Text("Search for an item")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
          focusManager.clearFocus()
        }),
        modifier = Modifier.padding(4.dp)
      )
      ChipRow(values = listOf("Tier 1", "Tier 2", "Tier 3", "Tier 4"), unselectable = true) {
        selectedTier = it?.let { it + 1 }
      }

      if (itemViewModel.error != null) {
        ErrorText(itemViewModel.error?.message ?: "An error occurred, please try reloading.")
      } else if (items.isEmpty()) {
        Loader()
      } else {
        LazyVerticalGrid(
          columns = GridCells.Fixed(3),
          modifier = Modifier.fillMaxSize()
        ) {
          items(items = filteredItems) { item ->
            Box(
              contentAlignment = Alignment.BottomCenter,
              modifier = Modifier
                //.height(128.dp)
                //.fillMaxWidth()
                .padding(8.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.extraLarge)
                .clickable {
                  itemViewModel.setItem(item)
                }
            ) {
              AsyncImage(
                model = item.itemIconURL,
                contentDescription = item.deviceName,
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center,
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
    AnimatedVisibility(selectedItem != null) {
      selectedItem?.let {
        ItemDetails(
          selectedItem!!,
          itemViewModel.itemIdMap.value,
          Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .clickable { itemViewModel.setItem(null) }
            .padding(16.dp)
        ) {
          itemViewModel.setItem(it)
        }
      }
    }
  }
}
