package com.matrix.presentation.ui.builds.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import com.matrix.presentation.Screen
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.ui.builds.BuildOverviewScreen
import com.matrix.presentation.ui.builds.BuildViewModel
import com.matrix.presentation.ui.builds.CreateBuildScreen

sealed class BuildsNavigation(val route: String) {
  object Builds: BuildsNavigation("builds")
  object CreateBuilds: BuildsNavigation("create_builds")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.buildsGraph(
  screen: Screen,
  navController: NavController
) {
  navigation(route = screen.route, startDestination = BuildsNavigation.Builds.route) {
    composable(
      BuildsNavigation.Builds.route,
      enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
      exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
    ) { backStackEntry ->
      val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(Screen.Builds.route)
      }
      val buildViewModel = hiltViewModel<BuildViewModel>(parentEntry)
      BuildOverviewScreen(
        buildViewModel,
        createBuild = { navController.navigate(BuildsNavigation.CreateBuilds.route) },
        modifier = Modifier
          .fillMaxSize()
          .statusBarsPadding()
          .imePadding()
      )
    }
    composable(
      BuildsNavigation.CreateBuilds.route,
      enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
      exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
    ) { backStackEntry ->
      val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(Screen.Builds.route)
      }
      val buildViewModel = hiltViewModel<BuildViewModel>(parentEntry)
      CreateBuildScreen(
        modifier = Modifier
          .fillMaxSize()
          .statusBarsPadding()
          .imePadding()
      )
    }
  }
}