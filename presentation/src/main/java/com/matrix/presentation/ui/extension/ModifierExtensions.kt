package com.matrix.presentation.ui.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.conditional(condition: Boolean, block: @Composable Modifier.() -> Modifier) =
  if (condition) {
    then(block(Modifier))
  } else {
    this
  }