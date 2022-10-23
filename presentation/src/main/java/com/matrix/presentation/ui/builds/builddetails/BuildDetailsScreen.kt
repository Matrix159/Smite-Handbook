package com.matrix.presentation.ui.builds.builddetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.GodTitleCard
import com.matrix.presentation.ui.components.Loader

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BuildDetailsScreen(
  buildDetailsViewModel: BuildDetailsViewModel,
  modifier: Modifier = Modifier,
) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    val collectedUiState by buildDetailsViewModel.uiState.collectAsStateWithLifecycle()
    when (val uiState = collectedUiState) {
      is BuildDetailsUiState.Error -> ErrorText(uiState.exception)
      BuildDetailsUiState.Loading -> Loader()
      is BuildDetailsUiState.Success -> {
        Text(text = "God", style = MaterialTheme.typography.titleMedium)
        Divider()
        Row(
          verticalAlignment = Alignment.Top,
          modifier = Modifier.fillMaxWidth()
        ) {
          GodTitleCard(
            godImageUrl = uiState.buildInformation.god.godIconURL,
            godName = uiState.buildInformation.god.name,
            godTitle = uiState.buildInformation.god.title,
            modifier = Modifier.fillMaxWidth()
          )
        }
        Text(text = "Items", style = MaterialTheme.typography.titleMedium)
        Divider()
        for (item in uiState.buildInformation.items) {
          AsyncImage(
            model = item.itemIconURL,
            contentDescription = item.deviceName
          )
        }
      }
    }
  }
}