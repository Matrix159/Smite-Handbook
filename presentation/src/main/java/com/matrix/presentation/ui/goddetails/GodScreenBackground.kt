package com.matrix.presentation.ui.goddetails

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.domain.models.GodInformation
import java.util.*
import kotlin.math.abs

@Composable
fun GodScreenBackground(
  selectedGod: GodInformation,
  offset: Pair<Float, Float>, // Current offset & Max offset
  modifier: Modifier = Modifier
) {

  val underscoreGodName = remember(selectedGod) {
    selectedGod.name
      .lowercase(Locale.getDefault())
      .replace(" ", "_")
      .replace("'", "")
  }
  val dashGodName = remember(selectedGod) {
    selectedGod.name
      .lowercase(Locale.getDefault())
      .replace(" ", "-")
      .replace("'", "")
  }
  val godNameNoSpaces = remember(selectedGod) {
    selectedGod.name
      .lowercase(Locale.getDefault())
      .replace(" ", "")
      .replace("'", "")
  }
  var image by remember(underscoreGodName, dashGodName) {
    mutableStateOf(
      "https://webcdn.hirezstudios.com/smite/god-skins/" +
        "${underscoreGodName}_standard-" +
        "${dashGodName}.jpg"
    )
  }

  Box(
    contentAlignment = Alignment.BottomCenter,
    modifier = modifier
  ) {
    AsyncImage(
      model = image,
      contentDescription = selectedGod.name,
      contentScale = ContentScale.Crop,
      alignment = Alignment.TopCenter,
      onError = {
        // Implement a fallback
        val fallbackUrl = "https://webcdn.hirezstudios.com/smite/god-skins/" +
          "${underscoreGodName}_standard-" +
          "${godNameNoSpaces}.jpg"
        if (image != fallbackUrl) {
          image = fallbackUrl
        }
      },
      alpha = 1 - abs(offset.first / offset.second),
      modifier = Modifier
        .matchParentSize()
        .offset(y = with(LocalDensity.current) { (offset.first * .10f).toDp() })
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
        .offset(y = with(LocalDensity.current) { (offset.first * .10f).toDp() })
    ) {
      val infiniteTransition = rememberInfiniteTransition()
      val iconOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -15f,
        animationSpec = infiniteRepeatable(
          animation = tween(800, easing = LinearEasing),
          repeatMode = RepeatMode.Reverse
        )
      )

      Icon(
        imageVector = Icons.Default.KeyboardArrowUp,
        contentDescription = "Drag Up",
        tint = Color.White,
        modifier = Modifier
          .size(32.dp)
          .offset(y = iconOffset.dp)
      )
      Text(
        text = selectedGod.name,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        style = MaterialTheme.typography.titleLarge
      )
      Text(
        text = selectedGod.title,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        style = MaterialTheme.typography.titleMedium
      )
    }
  }
}