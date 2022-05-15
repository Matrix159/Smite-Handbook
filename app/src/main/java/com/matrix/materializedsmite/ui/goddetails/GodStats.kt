package com.matrix.materializedsmite.ui.goddetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matrix.api.models.GodInformation
import kotlin.math.roundToInt

@Composable
fun GodStats(selectedGod: GodInformation, modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    var sliderValue by remember { mutableStateOf(1f) }
    Slider(
      value = sliderValue,
      onValueChange = { sliderValue = it },
      valueRange = 1f..20f,
      steps = 18,
      modifier = Modifier
        .padding(16.dp)
        .height(36.dp)
    )
    Text(sliderValue.roundToInt().toString())
  }
}