package com.matrix.materializedsmite.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbilityCard(abilityUrl: String, abilityName: String) {
  Card(modifier = Modifier
    .padding(bottom = 8.dp)
    .fillMaxWidth()) {
    Row {
      Image(
        painter = rememberImagePainter(abilityUrl),
        contentDescription = abilityName,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        modifier = Modifier
          .padding(4.dp)
          .clip(CircleShape)
          .size(64.dp)
      )
      Text(text = abilityName, style = MaterialTheme.typography.titleMedium)
    }
  }
}