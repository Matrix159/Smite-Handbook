package com.matrix.presentation.ui.builds

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.ItemInformation

@Composable
fun BuildOverviewCard(
  godInfo: GodInformation,
  items: List<ItemInformation>,
  modifier: Modifier = Modifier,
  onDelete: () -> Unit
) {
  Card(modifier = modifier.padding(16.dp)) {
    Column {
      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        AsyncImage(
          model = godInfo.godIconURL,
          contentDescription = godInfo.name,
          modifier = Modifier
            .padding(8.dp)
            .size(64.dp)
        )
        IconButton(onClick = onDelete) {
          Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
      }
      FlowRow {
        for (item in items) {
          AsyncImage(
            model = item.itemIconURL,
            contentDescription = item.deviceName,
            modifier = Modifier
              .padding(8.dp)
              .size(48.dp)
          )
        }
      }
    }
  }
}