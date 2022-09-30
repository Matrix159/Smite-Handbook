package com.matrix.presentation.ui.builds.buildlist

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matrix.domain.models.BuildInformation


//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun SwipeToDeleteCard(
//  modifier: Modifier = Modifier,
//  onDelete: () -> Unit,
//  content: @Composable () -> Unit,
//) {
//  val swipeableState = rememberSwipeableState(0)
//  var cardSize by remember { mutableStateOf(0) }
//  val anchors = remember(cardSize) {
//    mapOf(
//      -cardSize.toFloat() / 2 to -2,
//      -cardSize.toFloat() / 4 to -1,
//      0f to 0,
//      cardSize.toFloat() / 4 to 1,
//      cardSize.toFloat() / 2 to 2
//    ) // Maps anchor points (in px) to states
//  }
//
//  LaunchedEffect(swipeableState.currentValue) {
//    Timber.d("Current value: ${swipeableState.currentValue}")
//    when (swipeableState.currentValue) {
//      -2, 2 -> onDelete()
//    }
//  }
//
//  Box(modifier = modifier) {
//    Box(
//      modifier
//        .matchParentSize()
//        .background(Color.Red, shape = CardDefaults.shape)
//    ) {
//      Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier
//          .padding(16.dp)
//          .fillMaxSize()
//      ) {
//        Icon(Icons.Default.Delete, contentDescription = "Delete", modifier = Modifier.size(42.dp))
//        Icon(Icons.Default.Delete, contentDescription = "Delete", modifier = Modifier.size(42.dp))
//      }
//    }
//    Card(modifier = modifier
//      .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
//      .onGloballyPositioned { cardSize = it.size.width }
//      .let {
//        if (cardSize > 0) {
//          it.swipeable(
//            swipeableState,
//            anchors = anchors,
//            thresholds = { _, _ -> FractionalThreshold(0.3f) },
//            orientation = androidx.compose.foundation.gestures.Orientation.Horizontal
//          )
//        } else {
//          it
//        }
//      }
//    ) {
//      content()
//    }
//  }
//}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BuildOverviewCard(
  buildInformation: BuildInformation,
  modifier: Modifier = Modifier,
  onDelete: () -> Unit,
) {
  val dismissState = rememberDismissState(initialValue = DismissValue.Default) { state ->
    if (state == DismissValue.DismissedToEnd || state == DismissValue.DismissedToStart) {
      onDelete()
    }
    return@rememberDismissState true
  }

  val backgroundColor by animateColorAsState(
    targetValue = when (dismissState.targetValue) {
      DismissValue.Default -> Color.LightGray
      DismissValue.DismissedToEnd, DismissValue.DismissedToStart -> Color.Red
    }
  )
  val iconColor by animateColorAsState(
    targetValue = when (dismissState.targetValue) {
      DismissValue.Default -> Color.DarkGray
      DismissValue.DismissedToEnd, DismissValue.DismissedToStart -> Color.White
    }
  )

  val haptic = LocalHapticFeedback.current
  LaunchedEffect(dismissState.targetValue) {
    when (dismissState.targetValue) {
      DismissValue.Default -> {}
      DismissValue.DismissedToEnd, DismissValue.DismissedToStart -> haptic.performHapticFeedback(hapticFeedbackType = HapticFeedbackType.LongPress)
    }
  }

  SwipeToDismiss(
    state = dismissState,
    dismissThresholds = {
      FractionalThreshold(0.25f)
    },
    background = {
      val alignment = remember(dismissState.dismissDirection) {
        when(dismissState.dismissDirection) {
          DismissDirection.StartToEnd -> Alignment.CenterStart
          DismissDirection.EndToStart -> Alignment.CenterEnd
          null -> Alignment.CenterStart
        }
      }
      Box(
        modifier = Modifier
          .background(color = backgroundColor, shape = CardDefaults.shape)
          .padding(16.dp)
          .fillMaxSize()
      ) {
          Icon(
            Icons.Default.Delete,
            contentDescription = "Delete",
            modifier = Modifier
              .size(36.dp)
              .align(alignment),
            tint = iconColor
          )
      }
    },
    modifier = modifier
  ) {
    Card {
      Column {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.fillMaxWidth()
        ) {
          AsyncImage(
            model = buildInformation.god.godIconURL,
            contentDescription = buildInformation.god.name,
            modifier = Modifier
              .padding(8.dp)
              .size(64.dp)
          )
          Text(text = buildInformation.name ?: "", style = MaterialTheme.typography.titleLarge)
        }
        Row(
          horizontalArrangement = Arrangement.SpaceEvenly,
          modifier = Modifier.fillMaxWidth()
        ) {
          for (item in buildInformation.items) {
            AsyncImage(
              model = item.itemIconURL,
              contentDescription = item.deviceName,
              modifier = Modifier
                .weight(1f)
                .size(64.dp)
                .padding(8.dp)
            )
          }
        }
      }
    }
  }
}