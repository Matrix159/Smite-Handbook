package com.matrix.materializedsmite.ui.goddetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.matrix.materializedsmite.data.models.Ability

@Composable
fun AbilityImage(ability: Ability,
                 modifier: Modifier = Modifier,
                 selected: Boolean = false,
                 clicked: () -> Unit) {
  Image(
    painter = rememberImagePainter(ability.url),
    contentDescription = ability.summary,
    contentScale = ContentScale.Crop,
    alignment = Alignment.Center,
    modifier = modifier
      .clip(CircleShape)
      .size(64.dp).let {
        if (selected) it.border(1.dp, Color.Yellow, CircleShape) else it
      }.clickable(onClick = clicked)
  )
}