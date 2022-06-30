package com.matrix.materializedsmite.ui.itemdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.api.models.Item
import com.matrix.materializedsmite.R

@Composable
fun ItemDetails(
  item: Item,
  itemIdMap: Map<Long, Item>?,
  modifier: Modifier = Modifier,
  itemClicked: (item: Item) -> Unit
) {
  val tierMap: Map<Long, List<Item>> = remember(item, itemIdMap) {
    itemIdMap?.let {
      it.values.filter {
        it.childItemID == item.itemID // If I'm the child item ID of another item
          || it.rootItemID == item.itemID // If I'm the root item ID of another item
          || it.itemID == item.itemID // If this item is me
          || it.itemID == item.childItemID // If this item is one of my children items
          || it.itemID == item.rootItemID // If this item is my root item
      }
        .groupBy { it.itemTier }
        .toSortedMap()
    } ?: mapOf()
  }
  Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Top,
    modifier = modifier.verticalScroll(rememberScrollState())
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(item.deviceName)
      Row(verticalAlignment = Alignment.CenterVertically) {
        val totalCost: Long = remember(item, tierMap) {
          var cost = item.price
          for (tier in 1 until item.itemTier) {
            tierMap[tier]?.let {
              cost += if (it.isNotEmpty()) it[0].price else 0
            }
          }
          cost
        }
        Image(
          painterResource(R.drawable.coins),
          "Gold",
          Modifier
            .size(32.dp)
            .padding(4.dp)
        )
        Text(
          "${item.price}" + if (totalCost > 0) "($totalCost)" else "" ,
          modifier = Modifier.padding(4.dp)
        )
      }
    }
    Divider(thickness = 1.dp)

    Row(modifier = Modifier
      .padding(vertical = 8.dp)
      .fillMaxWidth()) {
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
          modifier = Modifier.padding(4.dp)
        )
      }
    }
    itemIdMap?.let {
      ItemTree(
        item,
        tierMap,
        itemClicked = { itemClicked(it) },
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}