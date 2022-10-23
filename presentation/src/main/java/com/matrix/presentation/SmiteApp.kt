package com.matrix.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.matrix.presentation.ui.GodList
import com.matrix.presentation.ui.ItemList
import com.matrix.presentation.ui.builds.BuildScreen
import com.matrix.presentation.ui.goddetails.GodScreen
import com.matrix.presentation.ui.itemdetails.ItemDetails
import com.matrix.presentation.viewmodels.BuildViewModel
import com.matrix.presentation.viewmodels.GodViewModel
import com.matrix.presentation.viewmodels.ItemViewModel


object NavigationRoutes {
  const val GodList = "GodList"
  const val GodDetails = "GodDetails"
  const val ItemList = "ItemList"
  const val ItemDetails = "ItemDetails"
  const val Builds = "Builds"
}

@OptIn(
  ExperimentalAnimationApi::class,
  ExperimentalMaterial3Api::class
)
@Composable
fun SmiteApp() {
  val navController: NavHostController = rememberAnimatedNavController()

  Scaffold(bottomBar = {
    val topScreens = listOf(Screen.Gods, Screen.Items, Screen.Builds)
    NavigationBar {

      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentDestination = navBackStackEntry?.destination

      topScreens.forEachIndexed { _, screen ->
        NavigationBarItem(
          icon = { Icon(painterResource(screen.iconResourceId), contentDescription = null) },
          label = { Text(stringResource(screen.resourceId)) },
          selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
          onClick = {
            val currentTab = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            // If we're not already in this navigation graph
            if (!currentTab) {
              navController.navigate(screen.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                  //saveState = true
                  // Do this to avoid double starting destinations on the stack
                  if (navController.graph.findStartDestination().hierarchy.any { it.route == screen.route }) {
                    inclusive = true
                  }
                }
              }
            }
          }
        )
      }
    }
  }) { paddingValues ->
    AnimatedNavHost(
      navController = navController,
      startDestination = Screen.Gods.route,
      modifier = Modifier.padding(paddingValues)
    ) {
      navigation(route = Screen.Gods.route, startDestination = NavigationRoutes.GodList) {
        composable(NavigationRoutes.GodList,
          enterTransition = {
            when (initialState.destination.route) {
              NavigationRoutes.GodDetails -> fadeIn()
              else -> null
            }
          },
          exitTransition = {
            when (targetState.destination.route) {
              NavigationRoutes.GodDetails -> fadeOut()
              else -> null
            }
          }
        ) { backStackEntry ->
          val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(Screen.Gods.route)
          }
          val godViewModel = hiltViewModel<GodViewModel>(parentEntry)

          GodList(godViewModel, modifier = Modifier.fillMaxSize()) { selectedGod ->
            // Clear the god, navigate, and then load the god as it navigates
            godViewModel.setGod(selectedGod)
            navController.navigate(NavigationRoutes.GodDetails)
          }
        }
        composable(NavigationRoutes.GodDetails,
          enterTransition = {
            when (initialState.destination.route) {
              NavigationRoutes.GodList -> expandIn(
                expandFrom = Alignment.Center,
              )
              else -> null
            }
          },
          exitTransition = {
            when (targetState.destination.route) {
              NavigationRoutes.GodList -> scaleOut()
              else -> null
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
      navigation(route = Screen.Items.route, startDestination = NavigationRoutes.ItemList) {
        composable(NavigationRoutes.ItemList) { backStackEntry ->
          val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(Screen.Items.route)
          }
          val itemViewModel = hiltViewModel<ItemViewModel>(parentEntry)
          ItemList(
            viewModel = itemViewModel,
            navController = navController,
            modifier = Modifier.fillMaxSize()
          )
        }
        composable(NavigationRoutes.ItemDetails) { backStackEntry ->
          val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(Screen.Items.route)
          }
          val itemViewModel = hiltViewModel<ItemViewModel>(parentEntry)

          val state = itemViewModel.uiState
          if (state.selectedItemInformation != null) {
            ItemDetails(
              state.selectedItemInformation,
              state.baseItemTreeNodes,
              Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                //.clickable { itemViewModel.setItem(null) }
                .padding(16.dp)
            ) {
              itemViewModel.setItem(it)
            }
          }
        }
      }
      navigation(route = Screen.Builds.route, startDestination = NavigationRoutes.Builds) {
        composable(NavigationRoutes.Builds) { backStackEntry ->
          val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(Screen.Builds.route)
          }
          val buildViewModel = hiltViewModel<BuildViewModel>(parentEntry)
          BuildScreen(
            buildViewModel,
            modifier = Modifier.fillMaxSize()
          )
        }
      }
    }
  }
}