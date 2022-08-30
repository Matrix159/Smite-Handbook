package com.matrix.presentation.ui.components.filters

import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun FilterGroup(
  title: String,
  content: @Composable () -> Unit
) {
  Text(style = MaterialTheme.typography.titleMedium, text = title)
  Divider(color = MaterialTheme.colorScheme.onSurface)
  FlowRow(mainAxisSpacing = 8.dp) {
    content()
  }
}