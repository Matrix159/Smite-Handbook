package com.matrix.presentation.ui.gods.goddetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matrix.shared.data.models.GodInformation

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