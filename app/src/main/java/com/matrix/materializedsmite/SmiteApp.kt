package com.matrix.materializedsmite

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.matrix.materializedsmite.ui.GodList
import com.matrix.materializedsmite.ui.GodScreen
import com.matrix.materializedsmite.viewmodels.SmiteViewModel


object NavigationRoutes {
  val GodList = "GodList"
  val GodDetails = "GodDetails"
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun SmiteApp() {
  val navController: NavHostController = rememberAnimatedNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()

  val smiteViewModel: SmiteViewModel = viewModel()
  AnimatedNavHost(navController = navController, startDestination = NavigationRoutes.GodList) {
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
          NavigationRoutes.GodList -> expandIn(expandFrom = Alignment.Center, animationSpec = tween(500))
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
}