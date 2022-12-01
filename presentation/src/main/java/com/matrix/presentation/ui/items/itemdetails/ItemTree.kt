package com.matrix.presentation.ui.items.itemdetails

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.presentation.utils.ItemNode
import com.matrix.shared.data.models.ItemInformation

val itemPadding = 8.dp
val iconSize = 48.dp

@Composable
fun ItemTree(
  baseNode: ItemNode,
  selectedItemInformation: ItemInformation,
  modifier: Modifier = Modifier,
  itemClicked: (itemInformation: ItemInformation) -> Unit,
) {
  // Wrap this is an outer column to have a parent that fills the max width
  Column(
    modifier = modifier.horizontalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    RecursiveItemTree(
      itemNode = baseNode,
      selectedItemInformation = selectedItemInformation,
      lengthOfTier = 1,
      indexInRow = 0,
      itemClicked = itemClicked
    )
  }
}

@Composable
private fun RecursiveItemTree(
  itemNode: ItemNode,
  selectedItemInformation: ItemInformation,
  lengthOfTier: Int,
  indexInRow: Int,
  itemClicked: (itemInformation: ItemInformation) -> Unit
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.width(IntrinsicSize.Min)
  ) {
    Row(verticalAlignment = Alignment.Bottom) {
      // Recursive tiers
      itemNode.children.forEachIndexed { index, childNode ->
        RecursiveItemTree(
          childNode,
          selectedItemInformation = selectedItemInformation,
          lengthOfTier = itemNode.children.size,
          indexInRow = index,
          itemClicked = itemClicked
        )
      }
    }
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxSize()
    ) {
      val lineColor = MaterialTheme.colorScheme.outline
      if (itemNode.children.isNotEmpty()) {
        Canvas(
          modifier = Modifier
            .fillMaxWidth()
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
      AsyncImage(
        model = itemNode.value.itemIconURL,
        contentDescription = itemNode.value.deviceName,
        modifier = Modifier
          .padding(horizontal = itemPadding)
          .requiredSize(iconSize)
          .clickable {
            itemClicked(itemNode.value)
          }.let {
            if (itemNode.value.itemID == selectedItemInformation.itemID) {
              return@let it.border(1.dp, MaterialTheme.colorScheme.outline)
            }
            it
          }
      )
      if (itemNode.parent != null) {
        Canvas(
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
        ) {
          drawLine(
            start = Offset(x = size.width / 2, y = 0f),
            end = Offset(x = size.width / 2, y = size.height),
            color = lineColor,
            strokeWidth = 5F
          )
          if (lengthOfTier > 1) {
            when (indexInRow) {
              0 -> {
                drawLine(
                  start = Offset(x = size.width / 2, y = size.height),
                  end = Offset(x = size.width, y = size.height),
                  color = lineColor,
                  strokeWidth = 5F
                )
              }
              lengthOfTier - 1 -> {
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
}