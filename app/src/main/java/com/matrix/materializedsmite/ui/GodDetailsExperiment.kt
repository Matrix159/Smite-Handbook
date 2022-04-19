package com.matrix.materializedsmite.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.matrix.materializedsmite.viewmodels.SmiteViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun GodDetailsExperiment(smiteAppViewModel: SmiteViewModel,
                         modifier: Modifier = Modifier) {
  val selectedGod = smiteAppViewModel.selectedGod.value
  val swipeState = rememberSwipeableState(initialValue = 0)
  val heightInPx: Float = with(LocalDensity.current) {
    LocalConfiguration.current.screenHeightDp.dp.toPx()
  }
  val heightInDp: Dp = LocalConfiguration.current.screenHeightDp.dp
  val anchors = mapOf(0f to 0, -heightInPx - 1f to 1)
  Log.d("SCREEN HEIGHT: ", LocalConfiguration.current.screenHeightDp.toString())
  Log.d("SWIPE STATE: ", with(LocalDensity.current) { swipeState.offset.value.toDp() }.toString())
  if (selectedGod != null) {
    Box(
      contentAlignment = Alignment.BottomCenter,
      modifier = Modifier.fillMaxSize(),
    ) {
      Image(
        painter = rememberImagePainter(selectedGod.godCardURL),
        contentDescription = selectedGod.name,
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter,
        modifier = Modifier
          .matchParentSize()
          .swipeable(
            state = swipeState,
            anchors = anchors,
            thresholds = { _, _ -> FractionalThreshold(0.3f) },
            orientation = Orientation.Vertical
          )
          .offset(y = with(LocalDensity.current) { (swipeState.offset.value * .10f).toDp() })
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
//      Row(
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.matchParentSize()
//      ) {
//        Icon(
//          imageVector = Icons.Default.KeyboardArrowLeft,
//          contentDescription = "Go to previous God",
//          modifier = Modifier.size(64.dp).background(Color(0x80000000)).clickable { smiteAppViewModel.goToPreviousGod() }
//        )
//        Icon(
//          imageVector = Icons.Default.KeyboardArrowRight,
//          contentDescription = "Go to next God",
//          modifier = Modifier.size(64.dp).background(Color(0x80000000)).clickable { smiteAppViewModel.goToNextGod() }
//        )
//      }
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
          .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
          .offset(y = with(LocalDensity.current) { (swipeState.offset.value * .10f).toDp() })
      ) {
        Icon(
          imageVector = Icons.Default.KeyboardArrowUp,
          contentDescription = "Drag Up",
          modifier = Modifier.size(32.dp)
        )
        Text(text = selectedGod.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
        Text(text = selectedGod.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
      }
      val detailsOffset: Dp =
        if (swipeState.currentValue == 0) heightInDp
        else with(LocalDensity.current) { (-swipeState.offset.value).toDp() }
      Surface(modifier = Modifier
        .fillMaxWidth()
        .swipeable(
          state = swipeState,
          anchors = anchors,
          thresholds = { _, _ -> FractionalThreshold(0.3f) },
          orientation = Orientation.Vertical
        )
        .height(with(LocalDensity.current) { (-swipeState.offset.value).toDp() })
        //.offset(y = )
      ) {
        Text("test")
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