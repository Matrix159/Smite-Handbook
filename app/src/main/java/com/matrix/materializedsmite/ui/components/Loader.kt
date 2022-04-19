package com.matrix.materializedsmite.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.matrix.materializedsmite.R


@Composable
fun Loader(modifier: Modifier = Modifier) {
  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
  val progress by animateLottieCompositionAsState(
    composition,
    iterations = LottieConstants.IterateForever
  )
  LottieAnimation(
    composition,
    progress,
    modifier = modifier,
  )
}