package com.matrix.materializedsmite

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.matrix.materializedsmite.ui.GodDetailsExperiment
import com.matrix.materializedsmite.ui.GodList
import com.matrix.materializedsmite.viewmodels.SmiteViewModel


object NavigationRoutes {
  val GodList = "GodList"
  val GotDetails = "GodDetails"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SmiteApp() {
  val navController: NavHostController = rememberAnimatedNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()

  val smiteViewModel: SmiteViewModel = viewModel()
  AnimatedNavHost(navController = navController, startDestination = NavigationRoutes.GodList) {
    composable(NavigationRoutes.GodList) {
      //val smiteViewModel = hiltViewModel<SmiteViewModel>()
      GodList(smiteViewModel) { selectedGod ->
        smiteViewModel.setGod(selectedGod)
        navController.navigate(NavigationRoutes.GotDetails)
      }
    }
    composable(NavigationRoutes.GotDetails) { GodDetailsExperiment(smiteViewModel) }
  }
}