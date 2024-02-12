package com.matrix.presentation.ui.gods.navigation

import android.net.Uri
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matrix.presentation.Screen
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.models.navigation.Route
import com.matrix.presentation.ui.gods.goddetails.GodDetailsViewModel
import com.matrix.presentation.ui.gods.goddetails.GodScreen
import com.matrix.presentation.ui.navigation.godListRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

sealed class GodsNavigation : Route {
  data object GodList : GodsNavigation() {
    override val route = "god_list"
  }

  data object GodDetails : GodsNavigation() {
    const val godIdArg = "godId"
    override val route = "god_details/{$godIdArg}"

    fun createNavigationRoute(godId: String): String = "god_details/${Uri.encode(godId)}"
  }
}

fun NavGraphBuilder.godsGraph(
  screen: Screen,
  navController: NavController,
) {
  navigation(route = screen.route, startDestination = GodsNavigation.GodList.route) {
    godListRoute(
      route = GodsNavigation.GodList.route,
      godClicked = {
        navController.navigate(GodsNavigation.GodDetails.createNavigationRoute(it.id.toString())) {
          launchSingleTop = true
        }
      }
    )
    composable(
      GodsNavigation.GodDetails.route,
      arguments = listOf(navArgument(GodsNavigation.GodDetails.godIdArg) {
        type = NavType.StringType
      }),
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
    ) {
      val godListViewModel = koinViewModel<GodDetailsViewModel>()

      GodScreen(godListViewModel, modifier = Modifier.fillMaxSize())
    }
  }
}