import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matrix.shared.data.models.GodInformation
import com.matrix.presentation.ui.gods.godlist.FilterableGodList

@Composable
fun GodSelectionView(
  gods: List<GodInformation>,
  godSelected: (god: GodInformation) -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(modifier = modifier) {
    FilterableGodList(
      gods = gods,
      godSelected = godSelected,
    )
  }
}