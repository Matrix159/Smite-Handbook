package com.matrix.presentation.ui.builds.navigation

import android.net.Uri
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.matrix.presentation.ui.builds.builddetails.BuildDetailsScreen
import com.matrix.presentation.ui.builds.builddetails.BuildDetailsViewModel
import com.matrix.presentation.ui.builds.buildlist.BuildListScreen
import com.matrix.presentation.ui.builds.buildlist.BuildListViewModel
import com.matrix.presentation.ui.builds.createbuild.CreateBuildScreen
import com.matrix.presentation.ui.builds.createbuild.CreateBuildViewModel
import com.matrix.presentation.ui.builds.itemselection.ItemSelectionScreen
import com.matrix.presentation.ui.builds.itemselection.ItemSelectionViewModel
import com.matrix.presentation.ui.navigation.godListRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

sealed interface BuildsNavigation : Route {
  data object Builds : BuildsNavigation {
    override val route = "builds"
  }

  data object CreateBuilds : BuildsNavigation {
    override val route = "create_builds"
  }

  data object GodList : BuildsNavigation {
    override val route: String = "builds_god_list"
    const val selectedGodId = "selectedGodId"
  }

  data object ItemList : BuildsNavigation {

    const val selectedItemIds = "selectedItemIds"
    const val initialItemIdsArg = "initialItemIds"
    override val route: String = "builds_item_list?$initialItemIdsArg={$initialItemIdsArg}"
    fun createNavigationRoute(initialItemIds: Array<Long> = emptyArray()): String {
      val params = if(initialItemIds.isNotEmpty()) "?initialItemIds=${initialItemIds.joinToString(",")}" else ""
      return "builds_item_list${params}"
    }
  }

  data object BuildDetails : BuildsNavigation {
    const val buildIdArg = "buildId"
    override val route = "build_details/{${buildIdArg}}"

    fun createNavigationRoute(buildId: String): String = "build_details/${Uri.encode(buildId)}"
  }
}

private fun NavGraphBuilder.itemSelectionRoute(navController: NavController) {
  composable(
    BuildsNavigation.ItemList.route,
    arguments = listOf(navArgument(BuildsNavigation.ItemList.initialItemIdsArg) {
      this.type = NavType.StringType
      this.defaultValue = ""
    }),
    enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
    exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
  ) {
    val viewModel: ItemSelectionViewModel = koinViewModel()
    ItemSelectionScreen(
      viewModel = viewModel,
      done = {
        navController.previousBackStackEntry
          ?.savedStateHandle
          ?.set(BuildsNavigation.ItemList.selectedItemIds, it)
        navController.popBackStack()
      },
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .imePadding()
    )
  }
}

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
      val buildListViewModel: BuildListViewModel = koinViewModel()
      BuildListScreen(
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
    ) { backStackEntry ->
      val selectedGodId =
         backStackEntry.savedStateHandle.getStateFlow<Long?>(BuildsNavigation.GodList.selectedGodId, null)
      val selectedItemIds = backStackEntry.savedStateHandle.getStateFlow<List<Long>>(BuildsNavigation.ItemList.selectedItemIds, emptyList())

      val createBuildViewModel: CreateBuildViewModel = koinViewModel(parameters =  { parametersOf(selectedGodId, selectedItemIds)})

      CreateBuildScreen(
        createBuildViewModel = createBuildViewModel,
        navigateToGodList = { navController.navigate(BuildsNavigation.GodList.route) },
        navigateToItemList = { navController.navigate(BuildsNavigation.ItemList.createNavigationRoute()) },
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
      arguments = listOf(navArgument(BuildsNavigation.BuildDetails.buildIdArg) {
        type = NavType.StringType
      }),
      enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
      exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
    ) {backStackEntry ->
      val selectedGodId =
        backStackEntry.savedStateHandle.getStateFlow<Long?>(BuildsNavigation.GodList.selectedGodId, null)
      val selectedItemIds = backStackEntry.savedStateHandle.getStateFlow<List<Long>>(BuildsNavigation.ItemList.selectedItemIds, emptyList())
      val buildDetailsViewModel: BuildDetailsViewModel = koinViewModel(parameters =  { parametersOf(selectedGodId, selectedItemIds)})

      BuildDetailsScreen(
        buildDetailsViewModel = buildDetailsViewModel,
        navigateToGodList = { navController.navigate(BuildsNavigation.GodList.route) },
        navigateToItemList = { navController.navigate(BuildsNavigation.ItemList.createNavigationRoute(it)) },
        onDeleteBuild = { navController.popBackStack() },
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
      }
    )
    itemSelectionRoute(navController)
  }
}