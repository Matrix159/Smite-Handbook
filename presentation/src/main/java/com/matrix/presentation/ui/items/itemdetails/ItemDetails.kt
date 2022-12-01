package com.matrix.presentation.ui.items.itemdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.matrix.presentation.R
import com.matrix.shared.data.models.ItemInformation

@Composable
fun ItemDetails(
  uiState: ItemDetailUiState.Success,
  itemClicked: (itemInformation: ItemInformation) -> Unit,
  modifier: Modifier = Modifier,
) {
  /**
   * <baseNode, currentNode>
   */
  val (baseNode, currentNode) = remember(uiState.itemTreeNodes, uiState.item) {
    uiState.itemTreeNodes.forEach {
      val foundItemNode = it.findItem(uiState.item)
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
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(uiState.item.deviceName)
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
          "${uiState.item.price}" + if (totalCost > 0) "($totalCost)" else "",
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
        model = uiState.item.itemIconURL,
        contentDescription = uiState.item.deviceName,
        modifier = Modifier.size(64.dp)
      )
      Text(
        uiState.item.shortDesc,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
      )
    }
    val description = remember(uiState.item.itemDescription.secondaryDescription) {
      uiState.item.itemDescription.secondaryDescription
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
      for (menuItem in uiState.item.itemDescription.menuItems) {
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
        selectedItemInformation = uiState.item,
        itemClicked = { itemClicked(it) },
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
      )
    }
  }
}