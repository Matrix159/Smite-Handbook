package com.matrix.presentation.ui.gods.navigation

import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable
import com.matrix.presentation.Screen
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.ui.gods.goddetails.GodDetailsViewModel
import com.matrix.presentation.ui.gods.goddetails.GodScreen
import com.matrix.presentation.ui.gods.godlist.GodList
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import timber.log.Timber

sealed class GodsNavigation(val route: String) {
  object GodList: GodsNavigation("god_list")
  object GodDetails: GodsNavigation("god_details/{godId}")
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
//      val parentEntry = remember(backStackEntry) {
//        navController.getBackStackEntry(Screen.Gods.route)
//      }
      val godListViewModel = hiltViewModel<GodListViewModel>()

      GodList(
        godListViewModel,
        modifier = Modifier
          .fillMaxSize()
          .statusBarsPadding()
          .imePadding()
      ) { selectedGod ->
        // TODO: Cleanup
        navController.navigate("god_details/${Uri.encode(selectedGod.id.toString())}") {
          launchSingleTop = true
        }
      }
    }
    composable(
      GodsNavigation.GodDetails.route,
      arguments = listOf(navArgument("godId") { type = NavType.StringType }),
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
      Timber.d(backStackEntry.arguments?.getString("godId"))
//      val parentEntry = remember(backStackEntry) {
//        navController.getBackStackEntry(Screen.Gods.route)
//      }
      val godListViewModel = hiltViewModel<GodDetailsViewModel>()

      GodScreen(godListViewModel, modifier = Modifier.fillMaxSize())
    }
  }
}