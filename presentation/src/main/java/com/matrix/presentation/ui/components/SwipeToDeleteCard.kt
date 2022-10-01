package com.matrix.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class SwipeStates {
  Default,
  Prompting,
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDeleteCard(
  modifier: Modifier = Modifier,
  onDelete: () -> Unit,
  content: @Composable () -> Unit,
) {
  val haptic = LocalHapticFeedback.current
  val swipeableState = rememberSwipeableState(SwipeStates.Default) {
    if (it == SwipeStates.Prompting) {
      haptic.performHapticFeedback(
        hapticFeedbackType = HapticFeedbackType.LongPress
      )
    }
    return@rememberSwipeableState true
  }
  var cardSize by remember { mutableStateOf(0) }

  val promptSize = with(LocalDensity.current) { 68.dp.toPx() }
  val anchors = remember(cardSize) {
    mapOf(
      -promptSize to SwipeStates.Prompting,
      0f to SwipeStates.Default,
    )
  }

  val backgroundColor by animateColorAsState(
    targetValue = when (swipeableState.targetValue) {
      SwipeStates.Default -> Color.LightGray
      SwipeStates.Prompting -> Color.Red
    },
  )
  val iconColor by animateColorAsState(
    targetValue = when (swipeableState.targetValue) {
      SwipeStates.Default -> Color.DarkGray
      SwipeStates.Prompting -> Color.White
    },
  )

  Box(modifier = modifier) {
    Box(
      modifier = Modifier
        .clip(CardDefaults.shape)
        .background(
          brush = Brush.horizontalGradient(listOf(Color.Transparent, backgroundColor)),
          shape = CardDefaults.shape
        )
        .clickable(onClick = onDelete)
        .padding(16.dp)
        .matchParentSize()
    ) {
      Icon(
        Icons.Default.Delete,
        contentDescription = "Delete",
        modifier = Modifier
          .size(36.dp)
          .align(Alignment.CenterEnd),
        tint = iconColor
      )
    }
    Card(modifier = modifier
      .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
      .onGloballyPositioned { cardSize = it.size.width }
      .let {
        if (cardSize > 0) {
          it.swipeable(
            swipeableState,
            anchors = anchors,
            thresholds = { state1, state2 ->
              FractionalThreshold(0.8f)
            },
            // This doesn't really seem to have an affect on swiping...
            velocityThreshold = Float.POSITIVE_INFINITY.dp,
            orientation = Orientation.Horizontal
          )
        } else {
          it
        }
      }
    ) {
      content()
    }
  }
}