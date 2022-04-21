package com.matrix.materializedsmite.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.matrix.materializedsmite.data.models.GodInformation
import java.util.*


@Composable
fun GodScreenBackground(
  selectedGod: GodInformation,
  offset: Float,
  modifier: Modifier = Modifier
) {
  val underscoreGodName = selectedGod.name.lowercase(Locale.getDefault()).replace(" ", "_")
  val dashGodName = selectedGod.name.lowercase(Locale.getDefault()).replace(" ", "-")
  Box(
    contentAlignment = Alignment.BottomCenter,
    modifier = modifier
  ) {
    Image(
      painter = rememberImagePainter("https://webcdn.hirezstudios.com/smite/god-skins/" +
        "${underscoreGodName}_standard-" +
        "${dashGodName}.jpg"),
      contentDescription = selectedGod.name,
      contentScale = ContentScale.Crop,
      alignment = Alignment.TopCenter,
      modifier = Modifier
        .matchParentSize()
        .offset(y = with(LocalDensity.current) { (offset * .10f).toDp() })
    )
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          brush = Brush.verticalGradient(
            0F to Color.Transparent,
            .5F to Color(0x40000000),
            .75f to Color(0x80000000),
            1f to Color(0xFF000000)
          )
        )
    )
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
        .offset(y = with(LocalDensity.current) { (offset * .10f).toDp() })
    ) {
      Icon(
        imageVector = Icons.Default.KeyboardArrowUp,
        contentDescription = "Drag Up",
        modifier = Modifier.size(32.dp)
      )
      Text(text = selectedGod.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
      Text(text = selectedGod.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
    }
  }
}