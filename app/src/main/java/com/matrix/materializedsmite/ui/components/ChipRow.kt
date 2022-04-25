package com.matrix.materializedsmite.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChipRow(values: List<String>, chipSelected: (index: Int) -> Unit) {
  var selectedChip by rememberSaveable { mutableStateOf(0) }
  Row(
    modifier = Modifier
      .padding(4.dp, 8.dp)
      .fillMaxWidth()
  ) {
    for ((index, value) in values.withIndex()) {
      Chip(
        text = value,
        selected = selectedChip == index,
        onClick = {
          selectedChip = index
          chipSelected(selectedChip)
        },
        modifier = Modifier
          .weight(1f)
          .padding(4.dp)
      )
    }
  }
}