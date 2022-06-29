package com.matrix.materializedsmite.ui.itemdetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.api.models.Item

@Composable
fun ItemTree(
  item: Item,
  itemIdMap: Map<Long, Item>,
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
    val lineColor = Color.Red
    val showRootItem = remember(item, itemIdMap) { item.itemID != item.rootItemID && itemIdMap.containsKey(item.rootItemID) }
    val showChildItem = remember(item, itemIdMap) { item.childItemID != item.rootItemID && itemIdMap.containsKey(item.childItemID) }
    val parents = remember(item, itemIdMap) { itemIdMap.values.filter { it.childItemID == item.itemID } }

    // Root item
    if (showRootItem) {
      AsyncImage(
        model = itemIdMap[item.rootItemID]!!.itemIconURL,
        contentDescription = item.deviceName,
        modifier = Modifier
          .size(iconSize)
          .clickable {
            itemClicked(itemIdMap[item.rootItemID]!!)
          }
      )
    }
    if (showRootItem) {
      Canvas(modifier = Modifier
        .width(24.dp)
        .height(1.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
          start = Offset(x = 0f, y = 0f),
          end = Offset(x = canvasWidth, y = 0f),
          color = lineColor,
          strokeWidth = 5F
        )
      }
    }

    // Child item
    if (showChildItem) {
      AsyncImage(
        model = itemIdMap[item.childItemID]!!.itemIconURL,
        contentDescription = item.deviceName,
        modifier = Modifier
          .size(iconSize)
          .clickable {
            itemClicked(itemIdMap[item.childItemID]!!)
          }
      )
    }

    if (showChildItem) {
      Canvas(modifier = Modifier
        .width(24.dp)
        .height(1.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
          start = Offset(x = 0f, y = 0f),
          end = Offset(x = canvasWidth, y = 0f),
          color = lineColor,
          strokeWidth = 5F
        )
      }
    }

    // Current Item
    AsyncImage(
      model = item.itemIconURL,
      contentDescription = item.deviceName,
      modifier = Modifier.size(iconSize)
    )

    // Any parent items
    if (parents.isNotEmpty()) {
      Canvas(modifier = Modifier
        .fillMaxHeight()
        .width(32.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val heightOfItem = iconSize.toPx() + 16.dp.toPx()
        // Draw starting line
        drawLine(
          start = Offset(x = 0f, y = canvasHeight / 2),
          end = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
          color = lineColor,
          strokeWidth = 5F
        )
        // Draw vertical line
        drawLine(
          start = Offset(x = canvasWidth / 2, y = 0f + (heightOfItem / 2)),
          end = Offset(x = canvasWidth / 2, y = canvasHeight - (heightOfItem / 2)),
          color = lineColor,
          strokeWidth = 5F
        )
        // Draw lines pointing to middle of each item
        for (counter in parents.indices) {
          drawLine(
            start = Offset(x = canvasWidth / 2, y = (heightOfItem / 2) + (heightOfItem * counter)),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
            end = Offset(x = canvasWidth, y = (heightOfItem / 2) + (heightOfItem * counter)),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
            color = lineColor,
            strokeWidth = 5F
          )
        }
      }
      Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.width(iconSize)
      ) {
        parents.forEach {
          AsyncImage(
            model = itemIdMap[it.itemID]!!.itemIconURL,
            contentDescription = it.deviceName,
            modifier = Modifier
              .padding(8.dp)
              .requiredSize(iconSize)
              .clickable {
                itemClicked(itemIdMap[it.itemID]!!)
              }
          )
        }
      }
    }
  }
}