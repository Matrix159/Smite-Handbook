package com.matrix.presentation.ui.builds.buildlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.presentation.ui.components.SwipeToDeleteCard
import com.matrix.shared.data.model.builds.BuildInformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildOverviewCard(
  buildInformation: BuildInformation,
  modifier: Modifier = Modifier,
  onDelete: () -> Unit,
  goToBuildDetails: (id: Long) -> Unit,
) {
  SwipeToDeleteCard(onDelete = onDelete, modifier = modifier) {
    Card(onClick = { goToBuildDetails(buildInformation.id!!) }) {
      Column {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.fillMaxWidth()
        ) {
          AsyncImage(
            model = buildInformation.god.godIconURL,
            contentDescription = buildInformation.god.name,
            modifier = Modifier
              .size(64.dp)
              .clip(RoundedCornerShape(bottomEnd = 12.dp))
          )
          Text(
            text = buildInformation.name ?: "",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
          )
        }
        Row(
          horizontalArrangement = Arrangement.Start,
          modifier = Modifier.padding(top = 8.dp)
        ) {
          for (item in buildInformation.items) {
            AsyncImage(
              model = item.itemIconURL,
              contentDescription = item.deviceName,
              modifier = Modifier
                .weight(1f, fill = false)
                .size(64.dp)
                .padding(8.dp)
                .clip(MaterialTheme.shapes.small)
            )
          }
        }
      }
    }
  }
}