package com.matrix.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.presentation.R
import com.matrix.presentation.ui.preview.SmiteHandbookPreviews
import com.matrix.presentation.ui.theme.SmiteHandbookTheme
import com.matrix.presentation.utils.debugPlaceholder

@Composable
fun GodTitleCard(
  godImageUrl: String,
  godName: String,
  godTitle: String,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
  ) {
    GodTitleCardContent(godImageUrl, godName, godTitle)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GodTitleCard(
  godImageUrl: String,
  godName: String,
  godTitle: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
    onClick = onClick
  ) {
    GodTitleCardContent(godImageUrl, godName, godTitle)
  }
}

@Composable
private fun GodTitleCardContent(
  godImageUrl: String,
  godName: String,
  godTitle: String,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.height(IntrinsicSize.Max)
  ) {
    AsyncImage(
      model = godImageUrl,
      contentDescription = godName,
      placeholder = debugPlaceholder(R.drawable.guardian),
      modifier = Modifier.fillMaxHeight()
    )
    Column(
      modifier = Modifier.padding(16.dp)
    ) {
      Text(
        text = godName,
        style = MaterialTheme.typography.titleLarge,
      )
      Text(
        text = godTitle,
        style = MaterialTheme.typography.bodyLarge,
        fontStyle = FontStyle.Italic
      )
    }
  }
}

@SmiteHandbookPreviews
@Composable
fun GodTitleCardPreview() {
  SmiteHandbookTheme {
    GodTitleCard(
      godImageUrl = "",
      godName = "SomeGuardian",
      godTitle = "The guardian",
    )
  }
}