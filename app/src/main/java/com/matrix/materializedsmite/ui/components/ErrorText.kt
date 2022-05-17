package com.matrix.materializedsmite.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorText(errorMessage: String) {
  Text(text = errorMessage,
    style = MaterialTheme.typography.bodyLarge,
    color = MaterialTheme.colorScheme.error
  )
}