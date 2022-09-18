package com.matrix.presentation.ui.gods.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import com.matrix.presentation.Screen
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.ui.gods.GodViewModel
import com.matrix.presentation.ui.gods.goddetails.GodScreen
import com.matrix.presentation.ui.gods.godlist.GodList

sealed class GodsNavigation(val route: String) {
  object GodList: GodsNavigation("god_list")
  object GodDetails: GodsNavigation("god_details")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.godsGraph(
  screen: Screen,
  navController: NavController
) {
  navigation(route = screen.route, startDestination = GodsNavigation.GodList.route) {
    composable(
      GodsNavigation.GodList.route,
      enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
      exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
    ) { backStackEntry ->
      val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(Screen.Gods.route)
      }
      val godViewModel = hiltViewModel<GodViewModel>(parentEntry)

      GodList(
        godViewModel,
        modifier = Modifier
          .fillMaxSize()
          .statusBarsPadding()
          .imePadding()
      ) { selectedGod ->
        // Set the god and navigate
        godViewModel.setGod(selectedGod)
        navController.navigate(GodsNavigation.GodDetails.route)
      }
    }
    composable(
      GodsNavigation.GodDetails.route,
      enterTransition = {
        when (initialState.destination.route) {
          GodsNavigation.GodList.route -> expandIn(
            expandFrom = Alignment.Center,
          )
          else -> fadeIn(defaultAnimationSpec)
        }
      },
      exitTransition = {
        when (targetState.destination.route) {
          GodsNavigation.GodList.route -> scaleOut()
          else -> fadeOut(animationSpec = defaultAnimationSpec)
        }
      }
    ) { backStackEntry ->
      val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(Screen.Gods.route)
      }
      val godViewModel = hiltViewModel<GodViewModel>(parentEntry)

      GodScreen(godViewModel, modifier = Modifier.fillMaxSize())
    }
  }
}