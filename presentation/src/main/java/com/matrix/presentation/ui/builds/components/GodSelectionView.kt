import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matrix.presentation.models.filters.AppliedGodFilters
import com.matrix.presentation.ui.gods.godlist.FilterableGodList
import com.matrix.shared.data.model.gods.GodInformation

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
      // TODO
      appliedFilters = AppliedGodFilters(),
      updateAppliedFilters = {}
    )
  }
}