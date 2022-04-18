package com.matrix.materializedsmite.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.matrix.materializedsmite.data.models.GodInformation
import com.matrix.materializedsmite.viewmodels.SmiteViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GodList(
  smiteViewModel: SmiteViewModel,
  godClicked: (godInfo: GodInformation) -> Unit
) {
  Column {
    val gods = smiteViewModel.gods.value
    if (gods.isEmpty()) {
      LaunchedEffect(true) {
        smiteViewModel.getGods()
      }
    }

    if (gods.isNotEmpty()) {
      LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize()
      ) {
        items(items = gods) { god ->
          Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
              .fillMaxWidth()
              .padding(8.dp)
              .clip(MaterialTheme.shapes.extraLarge)
              .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.extraLarge)
              .clickable { godClicked(god) }
          ) {
            Image(
              painter = rememberImagePainter(god.godCardURL),
              contentDescription = god.name,
              contentScale = ContentScale.Crop,
              alignment = Alignment.TopCenter,
              modifier = Modifier.height(180.dp).fillMaxWidth()
            )

//            Row(
//              modifier = Modifier.fillMaxSize(),
//              verticalAlignment = Alignment.CenterVertically,
//              horizontalArrangement = Arrangement.Center
//            ) {
//              Text(textAlign = TextAlign.Center, text = god.name)
//            }
            Box(
              modifier = Modifier
                .matchParentSize()
                .background(
                  brush = Brush.verticalGradient(
                    0F to Color.Transparent,
                    .5F to Color(0x40000000),
                    .75f to Color(0x80000000),
                    1f to Color(0xFF000000))
                )
            )
            Text(
              text = god.name,
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = Color.White,
              //style = MaterialTheme.typography.displayLarge,
              modifier = Modifier.padding(8.dp)
            )
          }
        }
      }
    }
  }
}