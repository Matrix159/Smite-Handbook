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
import com.matrix.domain.models.Item
import com.matrix.materializedsmite.R
import com.matrix.materializedsmite.utils.ItemNode

@Composable
fun ItemDetails(
  item: Item,
  itemIdMap: Map<Long, Item>?,
  itemTreeNodes: List<ItemNode>,
  modifier: Modifier = Modifier,
  itemClicked: (item: Item) -> Unit
) {
  val tierMap: Map<Long, List<Item>> = remember(item, itemIdMap) {
    val returnMap = itemIdMap?.values?.filter { filterItem ->
      filterItem.childItemID == item.itemID // If I'm the child item ID of another item
        || filterItem.rootItemID == item.itemID // If I'm the root item ID of another item
        || filterItem.itemID == item.itemID // If this item is me
        || filterItem.itemID == item.childItemID // If this item is one of my children items
        || filterItem.itemID == item.rootItemID // If this item is my root item
    }?.groupBy { it.itemTier }?.toMutableMap() ?: mutableMapOf<Long, List<Item>>()
    // API doesn't provide a way to see past 2 levels so when an outer tier is selected
    // need to do a manual lookup for the opposing tier (tier 1 & 4)
    if (returnMap.containsKey(2) && returnMap[2]!!.isNotEmpty()) {
      returnMap[1] = listOf(itemIdMap?.get(returnMap[2]!![0].childItemID)!!)
    }

    returnMap.toSortedMap()
  }

  /**
   * <baseNode, currentNode>
   */
  val (baseNode, currentNode) = remember(itemTreeNodes) {
    itemTreeNodes.forEach {
      val foundItemNode = it.findItem(item)
      if (foundItemNode != null) {
        return@remember Pair(it, foundItemNode)
      }
    }
    Pair(null, null)
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
        val totalCost = currentNode?.totalCost() ?: 0L
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
    baseNode?.let {
      ItemTree(
        tierMap,
        itemClicked = { itemClicked(it) },
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}