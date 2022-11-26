import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix159.shared.data.models.ItemInformation
import com.matrix.presentation.ui.items.itemlist.FilterableItemList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSelectionView(
  items: List<ItemInformation>,
  selectedItems: List<ItemInformation>,
  addSelectedItem: (item: ItemInformation) -> Unit,
  removeSelectedItem: (item: ItemInformation) -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(modifier = modifier) {
    Column {
      if (selectedItems.isNotEmpty()) {
        Row(
          horizontalArrangement = Arrangement.Start,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            //.height(88.dp)
            .padding(4.dp)
            .fillMaxWidth()
        ) {
          for (item in selectedItems) {
            Box(modifier = Modifier.weight(1f, false)) {
              AsyncImage(
                model = item.itemIconURL,
                contentDescription = item.deviceName,
                modifier = Modifier
                  .padding(8.dp)
                  .size(48.dp)
              )
              Badge(
                modifier = Modifier
                  .align(Alignment.TopEnd)
                  .clickable { removeSelectedItem(item) }
              ) {
                Icon(
                  Icons.Default.Close,
                  contentDescription = "remove",
                )
              }
            }
          }
        }
      }
      FilterableItemList(
        items = items,
        itemClicked = { item ->
          // Builds should only have 6 total items and no duplicates
          if (selectedItems.size < 6 && !selectedItems.contains(item)) {
            addSelectedItem(item)
          }
        },
        modifier = Modifier.weight(1f)
      )
    }
  }
}