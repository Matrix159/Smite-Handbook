package com.matrix.presentation.ui.itemdetails

import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matrix.presentation.ui.components.ChipRow

data class FilterValues(val temp: String = "")

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFilterModal(
    modifier: Modifier = Modifier,
    onFilterApplied: () -> Unit,
    content: @Composable (bottomSheetState: ModalBottomSheetState) -> Unit,
) {
    val bottomSheetState: ModalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetContentColor = MaterialTheme.colorScheme.onSurface,
        modifier = modifier,
        sheetContent = {
            ItemFilters()
        })
    {
        content(bottomSheetState)
    }
}

@Composable
fun ItemFilters(modifier: Modifier = Modifier) {
    ChipRow(values = listOf("Tier 1", "Tier 2", "Tier 3", "Tier 4"), unselectable = true) {
        //selectedTier = it?.let { it + 1 }
    }
}