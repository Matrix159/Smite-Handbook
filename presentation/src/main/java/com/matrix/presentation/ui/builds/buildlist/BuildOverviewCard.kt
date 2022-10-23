package com.matrix.presentation.ui.builds.buildlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.domain.models.BuildInformation
import com.matrix.presentation.ui.components.SwipeToDeleteCard

@Composable
fun BuildOverviewCard(
  buildInformation: BuildInformation,
  modifier: Modifier = Modifier,
  onDelete: () -> Unit,
) {
  SwipeToDeleteCard(onDelete = onDelete, modifier = modifier) {
    Card {
      Column {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.fillMaxWidth()
        ) {
          AsyncImage(
            model = buildInformation.god.godIconURL,
            contentDescription = buildInformation.god.name,
            modifier = Modifier
              .padding(8.dp)
              .size(64.dp)
          )
          Text(text = buildInformation.name ?: "", style = MaterialTheme.typography.titleLarge)
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
                .size(64.dp)
                .padding(8.dp)
            )
          }
        }
      }
    }
  }
}