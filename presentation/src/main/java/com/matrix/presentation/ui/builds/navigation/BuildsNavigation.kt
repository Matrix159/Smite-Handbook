package com.matrix.presentation.ui.builds.navigation

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import com.matrix.presentation.Screen
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.models.navigation.Route
import com.matrix.presentation.ui.builds.builddetails.BuildDetailsScreen
import com.matrix.presentation.ui.builds.builddetails.BuildDetailsViewModel
import com.matrix.presentation.ui.builds.buildlist.BuildOverviewScreen
import com.matrix.presentation.ui.builds.buildlist.BuildViewModel
import com.matrix.presentation.ui.builds.createbuild.CreateBuildScreen
import com.matrix.presentation.ui.builds.createbuild.CreateBuildViewModel
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import com.matrix.presentation.ui.items.itemlist.ItemListViewModel
import kotlinx.coroutines.launch

sealed interface BuildsNavigation: Route {
  object Builds : BuildsNavigation {
    override val route = "builds"
  }
  object CreateBuilds : BuildsNavigation {
    override val route = "create_builds"
  }
  object BuildDetails : BuildsNavigation {
    const val buildIdArg = "buildId"
    override val route = "build_details/{${buildIdArg}}"

    fun createNavigationRoute(buildId: String): String = "build_details/${Uri.encode(buildId)}"
  }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.buildsGraph(
  screen: Screen,
  navController: NavController,
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
        createBuild = {
          navController.navigate(BuildsNavigation.CreateBuilds.route) {
            launchSingleTop = true
          }
        },
        goToBuildDetails = {
          navController.navigate(BuildsNavigation.BuildDetails.createNavigationRoute(it.toString())) {
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
      BuildsNavigation.CreateBuilds.route,
      enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
      exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
    ) {
      val createBuildViewModel = hiltViewModel<CreateBuildViewModel>()
      val godListViewModel = hiltViewModel<GodListViewModel>()
      val itemListViewModel = hiltViewModel<ItemListViewModel>()

      CreateBuildScreen(
        createBuildViewModel = createBuildViewModel,
        godListViewModel = godListViewModel,
        itemListViewModel = itemListViewModel,
        done = {
          navController.popBackStack()
        },
        modifier = Modifier
          .fillMaxSize()
          .statusBarsPadding()
          .imePadding()
      )
    }
    composable(
      BuildsNavigation.BuildDetails.route,
      arguments = listOf(navArgument(BuildsNavigation.BuildDetails.buildIdArg) { type = NavType.StringType }),
      enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
      exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
    ) {
      val buildDetailsViewModel = hiltViewModel<BuildDetailsViewModel>()
      val coroutineScope = rememberCoroutineScope()
      BuildDetailsScreen(
        buildDetailsViewModel = buildDetailsViewModel,
        onDeleteBuild = { build ->
          coroutineScope.launch {
            // Delete after navigating to avoid the ViewModel possibly loading a non-existent build
            navController.popBackStack()
            buildDetailsViewModel.deleteBuild(build)
          }
        },
        modifier = Modifier
          .fillMaxSize()
          .statusBarsPadding()
          .imePadding()
      )
    }
  }
}