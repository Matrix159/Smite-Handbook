package com.matrix.presentation.ui.gods.goddetails

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix.presentation.ui.components.ErrorText
import com.matrix.presentation.ui.components.Loader

@OptIn(ExperimentalMaterialApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun GodScreen(
  godDetailsViewModel: GodDetailsViewModel,
  modifier: Modifier = Modifier
) {
  val _godDetailsUiState by godDetailsViewModel.godDetailsUiState.collectAsStateWithLifecycle()
  val swipeState = rememberSwipeableState(initialValue = 0)
  val scrollState = rememberScrollState()

  val heightInPx: Float = with(LocalDensity.current) {
    LocalConfiguration.current.screenHeightDp.dp.toPx()
  }
  val anchors = mapOf(heightInPx to 0, 0f to 1)

  val connection = remember {
    object : NestedScrollConnection {
      override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
      ): Offset {
        val delta = available.y
        return if (delta < 0) {
          Offset(x = 0f, y = swipeState.performDrag(delta))
        } else {
          Offset.Zero
        }
      }

      override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
      ): Offset {
        return Offset(x = 0f, y = swipeState.performDrag(available.y))
      }

      override suspend fun onPreFling(available: Velocity): Velocity {
        return if (available.y < 0 && scrollState.value == 0) {
          swipeState.performFling(available.y)
          available
        } else {
          Velocity.Zero
        }
      }

      override suspend fun onPostFling(
        consumed: Velocity,
        available: Velocity
      ): Velocity {
        swipeState.performFling(velocity = available.y)
        return super.onPostFling(consumed, available)
      }
    }
  }

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    when (val godDetailsUiState = _godDetailsUiState) {
      GodDetailsUiState.Loading -> Loader()
      is GodDetailsUiState.Error -> ErrorText(godDetailsUiState.exception.toString())
      is GodDetailsUiState.Success -> {
        Box(
          contentAlignment = Alignment.BottomCenter,
          modifier = modifier
            .fillMaxSize()
            .swipeable(
              state = swipeState,
              anchors = anchors,
              thresholds = { _, _ -> FractionalThreshold(0.2f) },
              orientation = Orientation.Vertical
            )
            .nestedScroll(connection)
        ) {
          GodScreenBackground(
            selectedGod = godDetailsUiState.godInformation,
            // Subtract height to "start" at 0 and then move upwards -Y, pass in maximum offset as well
            offset = Pair(swipeState.offset.value - heightInPx, -heightInPx),
            modifier = Modifier.matchParentSize()
          )
          GodDetails(
            godDetailsUiState,
            scrollState = scrollState,
            modifier = Modifier
              .matchParentSize()
              .offset(x = 0.dp, y = with(LocalDensity.current) { (swipeState.offset.value).toDp() })
          )
        }
      }
    }
  }
}

//@Preview
//@Composable
//fun Preview() {
//  val viewModel = SmiteAppViewModel()
//  viewModel.setGod(GodInformation())
//  GodDetails(smiteAppViewModel = SmiteAppViewModel())
//}