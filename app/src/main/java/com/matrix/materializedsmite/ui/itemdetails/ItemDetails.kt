package com.matrix.materializedsmite.ui.itemdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.api.models.Item

@Composable
fun ItemDetails(
  item: Item,
  modifier: Modifier = Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = modifier
  ) {
    AsyncImage(
      model = item.itemIconURL,
      contentDescription = item.deviceName,
      modifier = Modifier.size(64.dp)
    )
    Text(item.deviceName)
    Text(item.shortDesc)
    for (menuItem in item.itemDescription.menuItems) {
      Text("${menuItem.value} ${menuItem.description}")
    }
  }
}