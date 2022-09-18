package com.matrix.presentation.ui.builds

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.domain.models.BuildInformation

@Composable
fun BuildOverviewCard(
  buildInformation: BuildInformation,
  modifier: Modifier = Modifier,
  onDelete: () -> Unit
) {
  Card(modifier = modifier.padding(16.dp)) {
    Column {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          AsyncImage(
            model = buildInformation.god.godIconURL,
            contentDescription = buildInformation.god.name,
            modifier = Modifier
              .padding(8.dp)
              .size(64.dp)
          )
          Text(text = buildInformation.name ?: "", style = MaterialTheme.typography.titleLarge)
        }
        IconButton(onClick = onDelete) {
          Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
      }
      Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
      ) {
        for (item in buildInformation.items) {
          AsyncImage(
            model = item.itemIconURL,
            contentDescription = item.deviceName,
            modifier = Modifier
              .weight(1f)
              .padding(8.dp)
          )
        }
      }
    }
  }
}