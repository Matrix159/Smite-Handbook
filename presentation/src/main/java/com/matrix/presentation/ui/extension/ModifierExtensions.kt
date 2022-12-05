package com.matrix.presentation.ui.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.conditional(condition: Boolean, block: @Composable Modifier.() -> Modifier) =
  composed {
    if (condition) {
      then(block(Modifier))
    } else {
      this
    }
  }