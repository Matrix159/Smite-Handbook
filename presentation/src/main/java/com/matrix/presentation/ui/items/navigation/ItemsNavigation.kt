package com.matrix.presentation.ui.items.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import com.matrix.presentation.Screen
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.ui.ItemList
import com.matrix.presentation.ui.items.ItemViewModel
import com.matrix.presentation.ui.items.itemdetails.ItemDetails

sealed class ItemsNavigation(val route: String) {
  object ItemList: ItemsNavigation("item_list")
  object ItemDetails: ItemsNavigation("item_details")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.itemsGraph(
  screen: Screen,
  navController: NavController
) {
  navigation(route = screen.route, startDestination = ItemsNavigation.ItemList.route) {
    composable(
      ItemsNavigation.ItemList.route,
      enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
      exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
    ) { backStackEntry ->
      val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(Screen.Items.route)
      }
      val itemViewModel = hiltViewModel<ItemViewModel>(parentEntry)
      ItemList(
        viewModel = itemViewModel,
        navController = navController,
        modifier = Modifier
          .fillMaxSize()
          .statusBarsPadding()
          .imePadding()
      )
    }
    composable(
      ItemsNavigation.ItemDetails.route,
      enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
      exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
    ) { backStackEntry ->
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
            .padding(16.dp)
        ) {
          itemViewModel.setItem(it)
        }
      }
    }
  }
}