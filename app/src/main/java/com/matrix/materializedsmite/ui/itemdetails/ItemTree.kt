package com.matrix.materializedsmite.ui.itemdetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.api.models.Item

@Composable
fun ItemTree(
  item: Item,
  tierMap: Map<Long, List<Item>>,
  modifier: Modifier = Modifier,
  itemClicked: (item: Item) -> Unit,
) {
  Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .height(IntrinsicSize.Min)
  )
  {
    val iconSize = 64.dp
    val lineColor = MaterialTheme.colorScheme.outline
    //val showRootItem = remember(item, itemIdMap) { item.itemID != item.rootItemID && itemIdMap.containsKey(item.rootItemID) }
    //val showChildItem = remember(item, itemIdMap) { item.childItemID != item.rootItemID && itemIdMap.containsKey(item.childItemID) }
    // <tier, item>
//    val tierMap: Map<Long, List<Item>> = remember(item, itemIdMap) {
//      itemIdMap.values
//        .filter {
//          it.childItemID == item.itemID // If I'm the child item ID of another item
//            || it.rootItemID == item.itemID // If I'm the root item ID of another item
//            || it.itemID == item.itemID // If this item is me
//            || it.itemID == item.childItemID // If this item is one of my children items
//            || it.itemID == item.rootItemID // If this item is my root item
//        }
//        .groupBy { it.itemTier }
//        .toSortedMap()
//    }

//    val parents = remember(item, itemIdMap) {
//      itemIdMap.values.filter {
//        // Check if we're a child of this item or a root of this item (but make sure we're the same)
//        it.childItemID == item.itemID
//      }
//    }

    tierMap.forEach { (tier, list) ->
      val itemPadding = 8.dp
      val heightOfItemInDp = iconSize + (itemPadding * 2)
      val heightOfItemInPx = with(LocalDensity.current) {
        iconSize.toPx() + (itemPadding * 2).toPx()
      }

      Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.width(iconSize)
      ) {
        list.forEach {
          AsyncImage(
            model = it.itemIconURL,
            contentDescription = it.deviceName,
            modifier = Modifier
              .padding(itemPadding)
              .requiredSize(iconSize)
              .clickable {
                itemClicked(it)
              }
          )
        }
      }
      // Check if their is a next tier
      if (tierMap.containsKey(tier + 1)) {
        val lengthOfNextTier = tierMap[tier + 1]!!.size

        Canvas(modifier = Modifier
          .height(heightOfItemInDp * lengthOfNextTier)
          .width(32.dp)) {
          val canvasWidth = size.width
          val canvasHeight = size.height

          when (lengthOfNextTier) {
            1 -> { // Only want a straight line for 1 item
              drawLine(
                start = Offset(x = 0f, y = canvasHeight / 2),
                end = Offset(x = canvasWidth, y = canvasHeight / 2),
                color = lineColor,
                strokeWidth = 5F
              )
            }
            else -> { // Want a graph-like line for multiple items
              // Draw starting line
              drawLine(
                start = Offset(x = 0f, y = canvasHeight / 2),
                end = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                color = lineColor,
                strokeWidth = 5F
              )
              // Draw vertical line
              drawLine(
                start = Offset(x = canvasWidth / 2, y = 0f + (heightOfItemInPx / 2)),
                end = Offset(x = canvasWidth / 2, y = canvasHeight - (heightOfItemInPx / 2)),
                color = lineColor,
                strokeWidth = 5F
              )
              // Draw lines pointing to middle of each item
              for (counter in 0 until lengthOfNextTier) {
                drawLine(
                  start = Offset(x = canvasWidth / 2, y = (heightOfItemInPx / 2) + (heightOfItemInPx * counter)),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
                  end = Offset(x = canvasWidth, y = (heightOfItemInPx / 2) + (heightOfItemInPx * counter)),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
                  color = lineColor,
                  strokeWidth = 5F
                )
              }
            }
          }
        }
      }
    }

//    // Root item
//    if (showRootItem) {
//      AsyncImage(
//        model = itemIdMap[item.rootItemID]!!.itemIconURL,
//        contentDescription = item.deviceName,
//        modifier = Modifier
//          .size(iconSize)
//          .clickable {
//            itemClicked(itemIdMap[item.rootItemID]!!)
//          }
//      )
//    }
//    if (showRootItem) {
//      Canvas(modifier = Modifier
//        .width(24.dp)
//        .height(1.dp)) {
//        val canvasWidth = size.width
//        val canvasHeight = size.height
//        drawLine(
//          start = Offset(x = 0f, y = 0f),
//          end = Offset(x = canvasWidth, y = 0f),
//          color = lineColor,
//          strokeWidth = 5F
//        )
//      }
//    }
//
//    // Child item
//    if (showChildItem) {
//      AsyncImage(
//        model = itemIdMap[item.childItemID]!!.itemIconURL,
//        contentDescription = item.deviceName,
//        modifier = Modifier
//          .size(iconSize)
//          .clickable {
//            itemClicked(itemIdMap[item.childItemID]!!)
//          }
//      )
//    }
//
//    if (showChildItem) {
//      Canvas(modifier = Modifier
//        .width(24.dp)
//        .height(1.dp)) {
//        val canvasWidth = size.width
//        val canvasHeight = size.height
//        drawLine(
//          start = Offset(x = 0f, y = 0f),
//          end = Offset(x = canvasWidth, y = 0f),
//          color = lineColor,
//          strokeWidth = 5F
//        )
//      }
//    }
//
//    // Current Item
//    AsyncImage(
//      model = item.itemIconURL,
//      contentDescription = item.deviceName,
//      modifier = Modifier.size(iconSize)
//    )
//
//    // Any parent items
//    if (parents.isNotEmpty()) {
//      Canvas(modifier = Modifier
//        .fillMaxHeight()
//        .width(32.dp)) {
//        val canvasWidth = size.width
//        val canvasHeight = size.height
//
//        val heightOfItem = iconSize.toPx() + 16.dp.toPx()
//        // Draw starting line
//        drawLine(
//          start = Offset(x = 0f, y = canvasHeight / 2),
//          end = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
//          color = lineColor,
//          strokeWidth = 5F
//        )
//        // Draw vertical line
//        drawLine(
//          start = Offset(x = canvasWidth / 2, y = 0f + (heightOfItem / 2)),
//          end = Offset(x = canvasWidth / 2, y = canvasHeight - (heightOfItem / 2)),
//          color = lineColor,
//          strokeWidth = 5F
//        )
//        // Draw lines pointing to middle of each item
//        for (counter in parents.indices) {
//          drawLine(
//            start = Offset(x = canvasWidth / 2, y = (heightOfItem / 2) + (heightOfItem * counter)),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
//            end = Offset(x = canvasWidth, y = (heightOfItem / 2) + (heightOfItem * counter)),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
//            color = lineColor,
//            strokeWidth = 5F
//          )
//        }
//      }
//      Column(
//        verticalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier.width(iconSize)
//      ) {
//        parents.forEach {
//          AsyncImage(
//            model = itemIdMap[it.itemID]!!.itemIconURL,
//            contentDescription = it.deviceName,
//            modifier = Modifier
//              .padding(8.dp)
//              .requiredSize(iconSize)
//              .clickable {
//                itemClicked(itemIdMap[it.itemID]!!)
//              }
//          )
//        }
//      }
//    }
  }
}