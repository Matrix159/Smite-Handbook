package com.matrix.presentation.ui.itemdetails

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.domain.models.Item
import com.matrix.presentation.utils.ItemNode

@Composable
fun ItemTree(
  baseNode: ItemNode,
  tierMap: Map<Long, List<Item>>,
  modifier: Modifier = Modifier,
  itemClicked: (item: Item) -> Unit,
) {
  val iconSize = 48.dp
  val lineColor = MaterialTheme.colorScheme.outline
  val itemPadding = 8.dp

  val composablesToRender: MutableList<@Composable () -> Unit> = mutableListOf()

  composablesToRender.add {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(verticalAlignment = Alignment.Bottom) {
        // Tier 2
        baseNode.children.forEach {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.Bottom) {
              // Tier 3
              it.children.forEach {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                  Row(verticalAlignment = Alignment.Bottom) {
                    // Tier 4
                    val tier4Length = it.children.size
                    it.children.forEachIndexed { index, it ->
                      Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                          model = it.value.itemIconURL,
                          contentDescription = it.value.deviceName,
                          modifier = Modifier
                            .padding(horizontal = itemPadding)
                            .requiredSize(iconSize)
                            .clickable {
                              itemClicked(it.value)
                            }
                        )
                        Canvas(modifier = Modifier
                          .width(IntrinsicSize.Max)
                          .height(8.dp)
                          .border(1.dp, Color.Red)
                        ) {
                          drawLine(
                            start = Offset(x = size.width / 2, y = 0f),
                            end = Offset(x = size.width / 2, y = size.height),
                            color = lineColor,
                            strokeWidth = 5F
                          )
                          if (tier4Length > 1) {
                            when (index) {
                              0 -> {
                                drawLine(
                                  start = Offset(x = size.width / 2, y = size.height),
                                  end = Offset(x = size.width, y = size.height),
                                  color = lineColor,
                                  strokeWidth = 5F
                                )
                              }
                              tier4Length - 1 -> {
                                drawLine(
                                  start = Offset(x = size.width / 2, y = size.height),
                                  end = Offset(x = 0f, y = size.height),
                                  color = lineColor,
                                  strokeWidth = 5F
                                )
                              }
                              else -> {
                                drawLine(
                                  start = Offset(x = 0f, y = size.height),
                                  end = Offset(x = size.width, y = size.height),
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
                  Canvas(modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .height(8.dp)
                  ) {
                    drawLine(
                      start = Offset(x = size.width / 2, y = 0f),
                      end = Offset(x = size.width / 2, y = size.height),
                      color = lineColor,
                      strokeWidth = 5F
                    )
                  }
                  AsyncImage(
                    model = it.value.itemIconURL,
                    contentDescription = it.value.deviceName,
                    modifier = Modifier
                      .padding(horizontal = itemPadding)
                      .requiredSize(iconSize)
                      .clickable {
                        itemClicked(it.value)
                      }
                  )
                  Canvas(modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .height(8.dp)
                  ) {
                    drawLine(
                      start = Offset(x = size.width / 2, y = 0f),
                      end = Offset(x = size.width / 2, y = size.height),
                      color = lineColor,
                      strokeWidth = 5F
                    )
                  }
                }
              }
            }
            Canvas(modifier = Modifier
              .width(IntrinsicSize.Max)
              .height(8.dp)
            ) {
              drawLine(
                start = Offset(x = size.width / 2, y = 0f),
                end = Offset(x = size.width / 2, y = size.height),
                color = lineColor,
                strokeWidth = 5F
              )
            }
            AsyncImage(
              model = it.value.itemIconURL,
              contentDescription = it.value.deviceName,
              modifier = Modifier
                .padding(horizontal = itemPadding)
                .requiredSize(iconSize)
                .clickable {
                  itemClicked(it.value)
                }
            )
            Canvas(modifier = Modifier
              .width(IntrinsicSize.Max)
              .height(8.dp)
            ) {
              drawLine(
                start = Offset(x = size.width / 2, y = 0f),
                end = Offset(x = size.width / 2, y = size.height),
                color = lineColor,
                strokeWidth = 5F
              )
            }
          }
        }
      }
      Canvas(modifier = Modifier
        .width(IntrinsicSize.Max)
        .height(8.dp)
      ) {
        drawLine(
          start = Offset(x = size.width / 2, y = 0f),
          end = Offset(x = size.width / 2, y = size.height),
          color = lineColor,
          strokeWidth = 5F
        )
      }
      AsyncImage(
        model = baseNode.value.itemIconURL,
        contentDescription = baseNode.value.deviceName,
        modifier = Modifier
          .padding(horizontal = itemPadding)
          .requiredSize(iconSize)
          .clickable {
            itemClicked(baseNode.value)
          }
      )
    }
  }

  composablesToRender.reverse()
  composablesToRender.forEach { it() }

  //test()
//  Row(
//    horizontalArrangement = Arrangement.Center,
//    verticalAlignment = Alignment.CenterVertically,
//    modifier = modifier
//      .height(IntrinsicSize.Min)
//  )
//  {
//    tierMap.forEach { (tier, list) ->
//      val itemPadding = 8.dp
//      val heightOfItemInDp = iconSize + (itemPadding * 2)
//      val heightOfItemInPx = with(LocalDensity.current) {
//        iconSize.toPx() + (itemPadding * 2).toPx()
//      }
//
//      Column(
//        verticalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier.width(iconSize)
//      ) {
//        list.forEach {
//          AsyncImage(
//            model = it.itemIconURL,
//            contentDescription = it.deviceName,
//            modifier = Modifier
//              .padding(itemPadding)
//              .requiredSize(iconSize)
//              .clickable {
//                itemClicked(it)
//              }
//          )
//        }
//      }
//      // Check if there is a next tier
//      if (tierMap.containsKey(tier + 1)) {
//        val lengthOfNextTier = tierMap[tier + 1]!!.size
//
//        Canvas(
//          modifier = Modifier
//            .height(heightOfItemInDp * lengthOfNextTier)
//            .width(32.dp)
//        ) {
//          val canvasWidth = size.width
//          val canvasHeight = size.height
//
//          when (lengthOfNextTier) {
//            1 -> { // Only want a straight line for 1 item
//              drawLine(
//                start = Offset(x = 0f, y = canvasHeight / 2),
//                end = Offset(x = canvasWidth, y = canvasHeight / 2),
//                color = lineColor,
//                strokeWidth = 5F
//              )
//            }
//            else -> { // Want a graph-like line for multiple items
//              // Draw starting line
//              drawLine(
//                start = Offset(x = 0f, y = canvasHeight / 2),
//                end = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
//                color = lineColor,
//                strokeWidth = 5F
//              )
//              // Draw vertical line
//              drawLine(
//                start = Offset(x = canvasWidth / 2, y = 0f + (heightOfItemInPx / 2)),
//                end = Offset(x = canvasWidth / 2, y = canvasHeight - (heightOfItemInPx / 2)),
//                color = lineColor,
//                strokeWidth = 5F
//              )
//              // Draw lines pointing to middle of each item
//              for (counter in 0 until lengthOfNextTier) {
//                drawLine(
//                  start = Offset(
//                    x = canvasWidth / 2,
//                    y = (heightOfItemInPx / 2) + (heightOfItemInPx * counter)
//                  ),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
//                  end = Offset(
//                    x = canvasWidth,
//                    y = (heightOfItemInPx / 2) + (heightOfItemInPx * counter)
//                  ),//(realCanvasHeight * (counter / parents.size)) + iconSize.toPx()),
//                  color = lineColor,
//                  strokeWidth = 5F
//                )
//              }
//            }
//          }
//        }
//      }
//    }
//  }
}


//  val constraintSet = remember {
//    ConstraintSet {
//      val tier1 = createRefFor("tier1")
//      val tier2 = createRefFor("tier2")
//      val tier3 = createRefFor("tier3")
//      val tier4 = createRefFor("tier4")
//
//      constrain(tier1) {
//        bottom.linkTo(parent.bottom)
//      }
//      constrain(tier2) {
//        bottom.linkTo(tier1.top)
//      }
//      constrain(tier3) {
//        bottom.linkTo(tier2.top)
//      }
//      constrain(tier4) {
//        bottom.linkTo(tier3.top)
//      }
//    }
//  }
//  ConstraintLayout(
//    constraintSet = constraintSet,
//    modifier = Modifier
//    .fillMaxSize()
//    .border(1.dp, Color.Red)
//  ) {
//    val itemPadding = 8.dp
//
//    AsyncImage(
//      model = baseNode.value.itemIconURL,
//      contentDescription = baseNode.value.deviceName,
//      modifier = Modifier
//        .layoutId("tier1")
//        .padding(itemPadding)
//        .requiredSize(iconSize)
//        .clickable {
//          itemClicked(baseNode.value)
//        }
//    )
//
//    var currentNode: ItemNode? = baseNode
//    while (currentNode != null) {
//      Row(
//        modifier = Modifier
//          .layoutId("tier2")
//          .height(iconSize)
//      ) {
//        currentNode?.children?.forEach {
//          AsyncImage(
//            model = it.value.itemIconURL,
//            contentDescription = it.value.deviceName,
//            modifier = Modifier
//              .padding(itemPadding)
//              .requiredSize(iconSize)
//              .clickable {
//                itemClicked(it.value)
//              }
//          )
//        }
//      }
//      currentNode = null
//    }
//  }