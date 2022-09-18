package com.matrix.presentation.ui.items.itemdetails

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
import com.matrix.domain.models.ItemInformation
import com.matrix.presentation.R
import com.matrix.presentation.utils.ItemNode

@Composable
fun ItemDetails(
  itemInformation: ItemInformation,
  itemTreeNodes: List<ItemNode>,
  modifier: Modifier = Modifier,
  itemClicked: (itemInformation: ItemInformation) -> Unit
) {
//  val tierMap: Map<Long, List<Item>> = remember(item, itemIdMap) {
//    val returnMap = itemIdMap?.values?.filter { filterItem ->
//      filterItem.childItemID == item.itemID // If I'm the child item ID of another item
//        || filterItem.rootItemID == item.itemID // If I'm the root item ID of another item
//        || filterItem.itemID == item.itemID // If this item is me
//        || filterItem.itemID == item.childItemID // If this item is one of my children items
//        || filterItem.itemID == item.rootItemID // If this item is my root item
//    }?.groupBy { it.itemTier }?.toMutableMap() ?: mutableMapOf<Long, List<Item>>()
//    // API doesn't provide a way to see past 2 levels so when an outer tier is selected
//    // need to do a manual lookup for the opposing tier (tier 1 & 4)
//    if (returnMap.containsKey(2) && returnMap[2]!!.isNotEmpty()) {
//      returnMap[1] = listOf(itemIdMap?.get(returnMap[2]!![0].childItemID)!!)
//    }
//
//    returnMap.toSortedMap()
//  }

  /**
   * <baseNode, currentNode>
   */
  val (baseNode, currentNode) = remember(itemTreeNodes, itemInformation) {
    itemTreeNodes.forEach {
      val foundItemNode = it.findItem(itemInformation)
      if (foundItemNode != null) {
        return@remember Pair(it, foundItemNode)
      }
    }
    Pair(null, null)
  }

  Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Top,
    modifier = modifier
      .statusBarsPadding()
      .verticalScroll(rememberScrollState())
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(itemInformation.deviceName)
      Row(verticalAlignment = Alignment.CenterVertically) {
        val totalCost = remember(currentNode) { currentNode?.totalCost() ?: 0 }
        Image(
          painterResource(R.drawable.coins),
          "Gold",
          Modifier
            .size(32.dp)
            .padding(4.dp)
        )
        Text(
          "${itemInformation.price}" + if (totalCost > 0) "($totalCost)" else "",
          modifier = Modifier.padding(4.dp)
        )
      }
    }
    Divider(thickness = 1.dp)

    Row(
      modifier = Modifier
        .padding(vertical = 8.dp)
        .fillMaxWidth()
    ) {
      AsyncImage(
        model = itemInformation.itemIconURL,
        contentDescription = itemInformation.deviceName,
        modifier = Modifier.size(64.dp)
      )
      Text(
        itemInformation.shortDesc,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
      )
    }
    val description = remember(itemInformation.itemDescription.secondaryDescription) {
      itemInformation.itemDescription.secondaryDescription
        ?.replace("<n>", "\n")
        ?.replace("<.+>".toRegex(), "")
    }
    description?.let { it ->
      Text(
        it,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 8.dp)
      )
    }

    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.fillMaxWidth()
    ) {
      for (menuItem in itemInformation.itemDescription.menuItems) {
        Text(
          "${menuItem.value} ${menuItem.description}",
          style = MaterialTheme.typography.bodyMedium,
          modifier = Modifier.padding(4.dp)
        )
      }
    }
    baseNode?.let {
      ItemTree(
        baseNode,
        selectedItemInformation = itemInformation,
        itemClicked = { itemClicked(it) },
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
      )
    }
  }
}