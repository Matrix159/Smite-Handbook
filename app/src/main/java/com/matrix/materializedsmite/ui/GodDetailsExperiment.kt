package com.matrix.materializedsmite.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.matrix.materializedsmite.viewmodels.SmiteViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun GodDetailsExperiment(smiteAppViewModel: SmiteViewModel, modifier: Modifier = Modifier) {
  val selectedGod = smiteAppViewModel.selectedGod.value
  val swipeState = rememberSwipeableState(initialValue = 0)
  val anchors = mapOf(0f to 0, -LocalConfiguration.current.screenHeightDp.toFloat() - 1f to 1)
  Log.d("SCREEN HEIGHT: ", LocalConfiguration.current.screenHeightDp.toString())
  Log.d("SWIPE STATE: ", swipeState.offset.value.dp.toString())
  if (selectedGod != null) {
    Image(
      painter = rememberImagePainter(selectedGod.godCardURL),
      contentDescription = selectedGod.name,
      contentScale = ContentScale.Crop,
      alignment = Alignment.TopCenter,
      modifier = Modifier
        .fillMaxSize()
        .swipeable(
          state = swipeState,
          anchors = anchors,
          thresholds = { _, _ -> FractionalThreshold(0.3f) },
          orientation = Orientation.Vertical
        )
        .offset(y = swipeState.offset.value.dp)
    )
  }
}

//@Preview
//@Composable
//fun Preview() {
//  val viewModel = SmiteAppViewModel()
//  viewModel.setGod(GodInformation())
//  GodDetails(smiteAppViewModel = SmiteAppViewModel())
//}