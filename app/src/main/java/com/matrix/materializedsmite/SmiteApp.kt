package com.matrix.materializedsmite

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.matrix.materializedsmite.ui.GodList
import com.matrix.materializedsmite.ui.ItemList
import com.matrix.materializedsmite.ui.goddetails.GodScreen
import com.matrix.materializedsmite.ui.itemdetails.ItemDetails
import com.matrix.materializedsmite.viewmodels.GodViewModel
import com.matrix.materializedsmite.viewmodels.ItemViewModel


object NavigationRoutes {
  const val GodList = "GodList"
  const val GodDetails = "GodDetails"
  const val ItemList = "ItemList"
  const val ItemDetails = "ItemDetails"
}

@OptIn(
  ExperimentalAnimationApi::class,
  ExperimentalMaterial3Api::class
)
@Composable
fun SmiteApp() {
  val navController: NavHostController = rememberAnimatedNavController()

  Scaffold(bottomBar = {
    val topScreens = listOf(Screen.Gods, Screen.Items)
    NavigationBar {

      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentDestination = navBackStackEntry?.destination

      topScreens.forEachIndexed { _, screen ->
        NavigationBarItem(
          icon = { Icon(painterResource(screen.iconResourceId), contentDescription = null) },
          label = { Text(stringResource(screen.resourceId)) },
          selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
          onClick = {
            navController.navigate(screen.route) {
              // Pop up to the start destination of the graph to
              // avoid building up a large stack of destinations
              // on the back stack as users select items
              popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
              }
              // Avoid multiple copies of the same destination when
              // reselecting the same item
              launchSingleTop = true
              // Restore state when reselecting a previously selected item
              restoreState = true
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
      navigation(startDestination = NavigationRoutes.GodList, route = Screen.Gods.route) {
        composable(NavigationRoutes.GodList,
          enterTransition = {
            when (initialState.destination.route) {
              NavigationRoutes.GodDetails -> slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(500)
              )
              else -> null
            }
          },
          exitTransition = {
            when (targetState.destination.route) {
              NavigationRoutes.GodDetails -> fadeOut(animationSpec = tween(500))
              else -> null
            }
          }
        ) { backStackEntry ->
          val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(Screen.Gods.route)
          }
          val godViewModel = hiltViewModel<GodViewModel>(parentEntry)

          GodList(godViewModel) { selectedGod ->
            godViewModel.setGod(selectedGod)
            navController.navigate(NavigationRoutes.GodDetails)
          }
        }
        composable(NavigationRoutes.GodDetails,
          enterTransition = {
            when (initialState.destination.route) {
              NavigationRoutes.GodList -> expandIn(
                expandFrom = Alignment.Center,
                animationSpec = tween(500)
              )
              else -> null
            }
          },
          exitTransition = {
            when (targetState.destination.route) {
              NavigationRoutes.GodList -> fadeOut(animationSpec = tween(500))
              else -> null
            }
          }

        ) { backStackEntry ->
          val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(Screen.Gods.route)
          }
          val godViewModel = hiltViewModel<GodViewModel>(parentEntry)

          GodScreen(godViewModel)
        }
      }
      navigation(startDestination = NavigationRoutes.ItemList, route = Screen.Items.route) {
        composable(NavigationRoutes.ItemList) { backStackEntry ->
          val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(Screen.Items.route)
          }
          val itemViewModel = hiltViewModel<ItemViewModel>(parentEntry)
          ItemList(itemViewModel) {
            itemViewModel.setItem(it)
            navController.navigate(NavigationRoutes.ItemDetails)
          }
        }
        composable(NavigationRoutes.ItemDetails) { backStackEntry ->
          val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(Screen.Items.route)
          }
          val itemViewModel = hiltViewModel<ItemViewModel>(parentEntry)

          val item = itemViewModel.selectedItem.collectAsState().value
          if (item != null) {
            ItemDetails(item, modifier = Modifier.fillMaxSize())
          }
        }
      }
    }
  }
}