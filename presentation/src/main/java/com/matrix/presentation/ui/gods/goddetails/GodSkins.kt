package com.matrix.presentation.ui.gods.goddetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.matrix.shared.data.models.GodSkinInformation

@Composable
fun GodSkins(godSkinInformations: List<GodSkinInformation>, modifier: Modifier = Modifier) {
  FlowRow(modifier = modifier) {
    for (skin in godSkinInformations) {
      if (skin.godSkinURL.isNotBlank()) {
        Box(
          contentAlignment = Alignment.BottomCenter,
          modifier = Modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.extraLarge)
        ) {
          AsyncImage(
            model = skin.godSkinURL,
            contentDescription = skin.skinName,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxWidth()
          )
          Box(
            modifier = Modifier
              .matchParentSize()
              .background(
                brush = Brush.verticalGradient(
                  0F to Color.Transparent,
                  .5F to Color(0x40000000),
                  .75f to Color(0x80000000),
                  1f to Color(0xFF000000)
                )
              )
          )
          Text(
            text = skin.skinName,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White,
            //style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(8.dp)
          )
        }
      }
    }
  }
}