package com.matrix.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.matrix.presentation.R


@Composable
fun Loader(modifier: Modifier = Modifier) {
  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
  val progress by animateLottieCompositionAsState(
    composition,
    iterations = LottieConstants.IterateForever
  )
  LottieAnimation(
    composition,
    progress = { progress },
    modifier = modifier,
  )
}