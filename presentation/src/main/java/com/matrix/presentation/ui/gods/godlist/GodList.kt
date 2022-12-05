package com.matrix.presentation.ui.gods.godlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.matrix.shared.data.model.gods.GodInformation

@Composable
fun GodList(
  gods: List<GodInformation>,
  godClicked: (god: GodInformation) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(3),
    modifier = modifier
  ) {
    items(items = gods, key = { it.id }) { god ->
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