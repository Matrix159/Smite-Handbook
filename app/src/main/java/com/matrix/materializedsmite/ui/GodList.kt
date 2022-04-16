package com.matrix.materializedsmite.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    LaunchedEffect(true) {
      smiteViewModel.getGods()
    }
    if (gods.isNotEmpty()) {
      LazyVerticalGrid(cells = GridCells.Fixed(3), modifier = Modifier.fillMaxSize()) {
        items(items = gods) { god ->
          Card(modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { godClicked(god) }) {
            Image(
              painter = rememberImagePainter(god.godCardURL),
              contentDescription = "Smite god image",
              contentScale = ContentScale.Crop,
              alignment = Alignment.TopCenter,
              modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
            )
            Row(
              modifier = Modifier.fillMaxSize(),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center
            ) {
              Text(textAlign = TextAlign.Center, text = god.name)
            }

          }
        }
      }
    }
  }
}