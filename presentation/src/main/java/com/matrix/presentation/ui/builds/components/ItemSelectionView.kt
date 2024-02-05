
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matrix.shared.data.model.items.ItemInformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSelectionView(
  items: List<ItemInformation>,
  selectedItems: List<ItemInformation>,
  addSelectedItem: (item: ItemInformation) -> Unit,
  removeSelectedItem: (item: ItemInformation) -> Unit,
  modifier: Modifier = Modifier,
) {

}