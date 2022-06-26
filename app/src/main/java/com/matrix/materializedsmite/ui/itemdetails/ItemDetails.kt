package com.matrix.materializedsmite.ui.itemdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.api.models.Item
import com.matrix.materializedsmite.R
import com.matrix.materializedsmite.utils.getRoleResourceId
import timber.log.Timber

@Composable
fun ItemDetails(
  item: Item,
  itemIdMap: Map<Long, Item>?,
  modifier: Modifier = Modifier
) {
  Column(
    horizontalAlignment = Alignment.Start,
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

    Row(modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()) {
      AsyncImage(
        model = item.itemIconURL,
        contentDescription = item.deviceName,
        modifier = Modifier.size(64.dp)
      )
      Text(
        item.shortDesc,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
      )
    }

    item.itemDescription.secondaryDescription?.let {
      Text(
        item.itemDescription.secondaryDescription!!,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 8.dp)
      )
    }

    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.fillMaxWidth()
    ) {
      for (menuItem in item.itemDescription.menuItems) {
        Text(
          "${menuItem.value} ${menuItem.description}",
          style = MaterialTheme.typography.bodyMedium,
          modifier = Modifier.padding(4.dp))
      }
    }

    itemIdMap?.let {
      if (itemIdMap.containsKey(item.rootItemID)) {
        AsyncImage(
          model = itemIdMap[item.rootItemID]!!.itemIconURL,
          contentDescription = item.deviceName,
          modifier = Modifier.size(64.dp)
        )
      }
      if (itemIdMap.containsKey(item.childItemID)) {
        AsyncImage(
          model = itemIdMap[item.childItemID]!!.itemIconURL,
          contentDescription = item.deviceName,
          modifier = Modifier.size(64.dp)
        )
      }
    }
  }
}