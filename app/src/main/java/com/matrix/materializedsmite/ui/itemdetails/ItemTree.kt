package com.matrix.materializedsmite.ui.itemdetails

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.api.models.Item
import com.matrix.materializedsmite.viewmodels.ItemNode

@Composable
fun ItemTree(
  //itemNode: ItemNode,
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

        Canvas(
          modifier = Modifier
            .height(heightOfItemInDp * lengthOfNextTier)
            .width(32.dp)
        ) {
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
                  start = Offset(
                    x = canvasWidth / 2,
                    y = (heightOfItemInPx / 2) + (heightOfItemInPx * counter)
                  ),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
                  end = Offset(
                    x = canvasWidth,
                    y = (heightOfItemInPx / 2) + (heightOfItemInPx * counter)
                  ),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
                  color = lineColor,
                  strokeWidth = 5F
                )
              }
            }
          }
        }
      }
    }
  }
}