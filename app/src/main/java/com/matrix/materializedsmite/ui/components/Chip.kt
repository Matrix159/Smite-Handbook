package com.matrix.materializedsmite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
  text: String,
  modifier: Modifier = Modifier,
  selected: Boolean = false,
  onClick: () -> Unit
) {
  var localModifier = modifier
    .clip(CircleShape)
    .background(MaterialTheme.colorScheme.background)
  localModifier =
    if (selected) localModifier.border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
    else localModifier.border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
  Box(modifier = localModifier.clickable(onClick = onClick)) {
    Text(
      text = text,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.labelLarge,
      modifier = Modifier
        .padding(30.dp, 8.dp)
        .fillMaxWidth()
    )
  }
}