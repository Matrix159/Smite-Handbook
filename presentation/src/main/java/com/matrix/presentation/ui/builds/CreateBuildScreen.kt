package com.matrix.presentation.ui.builds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateBuildScreen(modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    for (step in 1..3) {
      Step(stepNumber = step)
    }
  }
}

@Composable
fun Step(stepNumber: Int, modifier: Modifier = Modifier) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
      .background(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = CircleShape
      )
      .size(48.dp) // TODO: Check out custom layouts
  ) {
    Text(text = stepNumber.toString())
  }
}