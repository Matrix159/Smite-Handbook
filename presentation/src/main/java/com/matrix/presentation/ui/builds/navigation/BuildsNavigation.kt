package com.matrix.presentation.ui.builds.navigation

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navigation
import androidx.navigation.compose.composable
import com.matrix.presentation.Screen
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.models.navigation.Route
import com.matrix.presentation.ui.builds.builddetails.BuildDetailsScreen
import com.matrix.presentation.ui.builds.builddetails.BuildDetailsViewModel
import com.matrix.presentation.ui.builds.buildlist.BuildListViewModel
import com.matrix.presentation.ui.builds.buildlist.BuildOverviewScreen
import com.matrix.presentation.ui.builds.createbuild.CreateBuildScreen
import com.matrix.presentation.ui.builds.createbuild.CreateBuildViewModel
import com.matrix.presentation.ui.gods.navigation.GodsNavigation
import com.matrix.presentation.ui.gods.navigation.godListRoute
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

sealed interface BuildsNavigation: Route {
  data object Builds : BuildsNavigation {
    override val route = "builds"
  }
  data object CreateBuilds : BuildsNavigation {
    override val route = "create_builds"
  }
  data object GodList: BuildsNavigation {
    override val route: String = "builds_god_list"
    const val selectedGodId = "selectedGodId"
  }

  data object BuildDetails : BuildsNavigation {
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
    ) { _ ->
//      val parentEntry = remember(backStackEntry) {
//        navController.getBackStackEntry(Screen.Builds.route)
//      }
      //val buildViewModel = hiltViewModel<BuildViewModel>(parentEntry)
      val buildListViewModel: BuildListViewModel = koinViewModel()
      BuildOverviewScreen(
        buildListViewModel,
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
    ) {backStackEntry ->
      val selectedGodId = backStackEntry.savedStateHandle.get<Long>(BuildsNavigation.GodList.selectedGodId)
      val createBuildViewModel: CreateBuildViewModel = koinViewModel()
      createBuildViewModel.savedStateHandle[BuildsNavigation.GodList.selectedGodId] = selectedGodId

      CreateBuildScreen(
        createBuildViewModel = createBuildViewModel,
        navigateToGodList = { navController.navigate(BuildsNavigation.GodList.route) },
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
      val buildDetailsViewModel: BuildDetailsViewModel = koinViewModel()
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
    godListRoute(
      route = BuildsNavigation.GodList.route,
      godClicked = {
        navController.previousBackStackEntry
          ?.savedStateHandle
          ?.set(BuildsNavigation.GodList.selectedGodId, it.id)
        navController.popBackStack()
//        navController.navigate(GodsNavigation.GodDetails.createNavigationRoute(it.id.toString())) {
//          launchSingleTop = true
//        }
      }
    )
  }
}