package com.matrix.presentation.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.matrix.presentation.R

@Composable
fun ErrorText(errorMessage: String, modifier: Modifier = Modifier) {
  Text(
    text = errorMessage,
    style = MaterialTheme.typography.bodyLarge,
    color = MaterialTheme.colorScheme.error,
    modifier = modifier
  )
}

@Composable
fun ErrorText(throwable: Throwable?, modifier: Modifier = Modifier) = ErrorText(
  errorMessage = throwable?.toString() ?: stringResource(R.string.unknown_error),
  modifier
)