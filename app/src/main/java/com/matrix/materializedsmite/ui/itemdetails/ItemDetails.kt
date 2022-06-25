package com.matrix.materializedsmite.ui.itemdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.api.models.Item
import com.matrix.materializedsmite.R
import com.matrix.materializedsmite.utils.getRoleResourceId

@Composable
fun ItemDetails(
  item: Item,
  modifier: Modifier = Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top,
    modifier = modifier
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(item.deviceName)
      Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
          painterResource(R.drawable.coins),
          "Gold",
          Modifier
            .size(32.dp)
            .padding(4.dp)
        )
        Text(
          "${item.price}",
          modifier = Modifier.padding(4.dp)
        )
      }
    }
    Divider(thickness = 1.dp)
    AsyncImage(
      model = item.itemIconURL,
      contentDescription = item.deviceName,
      modifier = Modifier.size(82.dp)
    )

    Text(item.shortDesc, modifier = Modifier.padding(4.dp))
    Text(item.itemDescription.secondaryDescription ?: "", modifier = Modifier.padding(4.dp))
    for (menuItem in item.itemDescription.menuItems) {
      Text("${menuItem.value} ${menuItem.description}", modifier = Modifier.padding(4.dp))
    }
  }
}