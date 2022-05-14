package com.matrix.materializedsmite

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.matrix.materializedsmite.viewmodels.SmiteViewModel


object NavigationRoutes {
  val GodList = "GodList"
  val GodDetails = "GodDetails"
}

@OptIn(
  ExperimentalAnimationApi::class, ExperimentalMaterialApi::class,
  ExperimentalMaterial3Api::class
)
@Composable
fun SmiteApp() {
  val smiteViewModel: SmiteViewModel = viewModel()
  val navController: NavHostController = rememberAnimatedNavController()

  Scaffold(bottomBar = {
    val topScreens = listOf(Screen.Gods, Screen.Items)
    NavigationBar {
      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentDestination = navBackStackEntry?.destination
      topScreens.forEachIndexed { index, screen ->
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
        ) {
          //val smiteViewModel = hiltViewModel<SmiteViewModel>()
          GodList(smiteViewModel) { selectedGod ->
            smiteViewModel.setGod(selectedGod)
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

        ) {
          GodScreen(smiteViewModel)
        }
      }
      navigation(startDestination = "itemList", route = Screen.Items.route) {
        composable("itemList") {
          ItemList(smiteViewModel = smiteViewModel)
        }
      }
    }
  }
}