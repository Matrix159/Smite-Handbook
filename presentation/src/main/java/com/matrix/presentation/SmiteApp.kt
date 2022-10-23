package com.matrix.presentation

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.matrix.presentation.ui.builds.navigation.buildsGraph
import com.matrix.presentation.ui.gods.navigation.godsGraph
import com.matrix.presentation.ui.items.navigation.itemsGraph
import com.matrix.presentation.ui.navigation.SmiteNavigationBar


val defaultAnimationSpec: FiniteAnimationSpec<Float> = tween(300)

@OptIn(
  ExperimentalAnimationApi::class,
  ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class
)
@Composable
fun SmiteApp() {
  val navController: NavHostController = rememberAnimatedNavController()
  val configuration = LocalConfiguration.current
  var isLandScape by remember { mutableStateOf(false) }
  isLandScape = when (configuration.orientation) {
    Configuration.ORIENTATION_LANDSCAPE -> {
      true
    }
    else -> {
      false
    }
  }
  Scaffold(
    bottomBar = {
      val topLevelDestinations = remember { listOf(Screen.Gods, Screen.Items, Screen.Builds) }
      SmiteNavigationBar(
        topLevelDestinations = topLevelDestinations,
        navController = navController
      )
    },
    contentWindowInsets = WindowInsets(0, 0, 0, 0)
  ) { paddingValues ->
    AnimatedNavHost(
      navController = navController,
      startDestination = Screen.Gods.route,
      modifier = Modifier
        .padding(paddingValues)
        .consumedWindowInsets(paddingValues)
        // The navigation bar padding is handled by bottomnavbar in portrait, but
        // we need to add it on for when it's on the sides in landscape
        .let {
          if (isLandScape) {
            it.navigationBarsPadding()
          } else {
            it
          }
        }
    ) {
      godsGraph(screen = Screen.Gods, navController = navController)
      itemsGraph(screen = Screen.Items, navController = navController)
      buildsGraph(screen = Screen.Builds, navController = navController)
    }
  }
}