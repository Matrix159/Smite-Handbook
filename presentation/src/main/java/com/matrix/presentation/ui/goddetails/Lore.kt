package com.matrix.presentation.ui.goddetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matrix.domain.models.GodInformation

@Composable
fun Lore(god: GodInformation, modifier: Modifier = Modifier) {
  var visible by remember { mutableStateOf(false) }
  AnimatedVisibility(
    visible = visible,
    modifier = modifier,
  ) {
    Text(
      god.lore.replace("\\n", "\r\n"),
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(16.dp)
    )
  }
  LaunchedEffect(true) {
    visible = true
  }
}