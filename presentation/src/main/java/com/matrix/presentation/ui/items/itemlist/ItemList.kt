package com.matrix.presentation.ui.items.itemlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.matrix159.shared.data.models.ItemInformation

@Composable
fun ItemList(
  items: List<ItemInformation>,
  itemClicked: (item: ItemInformation) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(3),
    modifier = modifier
  ) {
    items(items = items, key = { it.itemID }) { item ->
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
            itemClicked(item)
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
}