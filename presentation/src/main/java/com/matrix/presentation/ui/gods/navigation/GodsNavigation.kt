package com.matrix.presentation.ui.gods.navigation

import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.matrix.presentation.Screen
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.models.navigation.Route
import com.matrix.presentation.ui.gods.goddetails.GodDetailsViewModel
import com.matrix.presentation.ui.gods.goddetails.GodScreen
import com.matrix.presentation.ui.gods.godlist.GodListScreen
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.navigation.koinNavGraphViewModel

sealed class GodsNavigation: Route {
  object GodList: GodsNavigation() {
    override val route = "god_list"
  }
  object GodDetails: GodsNavigation() {
    const val godIdArg = "godId"
    override val route = "god_details/{$godIdArg}"

    fun createNavigationRoute(godId: String): String = "god_details/${Uri.encode(godId)}"
  }
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
    ) {
      val godListViewModel = koinViewModel<GodListViewModel>()

      GodListScreen(
        godListViewModel,
        godClicked = {
          navController.navigate(GodsNavigation.GodDetails.createNavigationRoute(it.id.toString())) {
            launchSingleTop = true
          }
        },
        modifier = Modifier
          .fillMaxSize()
          .statusBarsPadding()
          .imePadding()
      )
    }
    composable(
      GodsNavigation.GodDetails.route,
      arguments = listOf(navArgument(GodsNavigation.GodDetails.godIdArg) { type = NavType.StringType }),
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