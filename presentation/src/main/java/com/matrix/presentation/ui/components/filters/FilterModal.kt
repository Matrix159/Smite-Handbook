package com.matrix.presentation.ui.components.filters

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterModal(
  modifier: Modifier = Modifier,
  sheetContent: @Composable ColumnScope.() -> Unit,
  content: @Composable (bottomSheetState: ModalBottomSheetState) -> Unit,
) {
  val bottomSheetState: ModalBottomSheetState =
    rememberModalBottomSheetState(
      initialValue = ModalBottomSheetValue.Hidden,
      skipHalfExpanded = true
    )
  ModalBottomSheetLayout(
    sheetState = bottomSheetState,
    sheetBackgroundColor = MaterialTheme.colorScheme.surface,
    sheetContentColor = MaterialTheme.colorScheme.onSurface,
    modifier = modifier,
    sheetContent = sheetContent
  ) {
    content(bottomSheetState)
  }
}
