package com.matrix.materializedsmite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.matrix.materializedsmite.ui.GodDetails
import com.matrix.materializedsmite.ui.GodList
import com.matrix.materializedsmite.viewmodels.SmiteViewModel


object NavigationRoutes {
  val GodList = "GodList"
  val GotDetails = "GodDetails"
}

@Composable
fun SmiteApp() {
  val navController: NavHostController = rememberNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()

  val smiteViewModel: SmiteViewModel = viewModel()
  NavHost(navController = navController, startDestination = NavigationRoutes.GodList) {
    composable(NavigationRoutes.GodList) {
      //val smiteViewModel = hiltViewModel<SmiteViewModel>()
      GodList(smiteViewModel) { selectedGod ->
        smiteViewModel.setGod(selectedGod)
        navController.navigate(NavigationRoutes.GotDetails)
      }
    }
    composable(NavigationRoutes.GotDetails) { GodDetails(smiteViewModel) }
  }
}