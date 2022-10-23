package com.matrix.presentation.ui.items.navigation

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable
import com.matrix.presentation.Screen
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.models.navigation.Route
import com.matrix.presentation.ui.items.itemdetails.ItemDetailsScreen
import com.matrix.presentation.ui.items.itemdetails.ItemDetailsViewModel
import com.matrix.presentation.ui.items.itemlist.ItemListScreen
import com.matrix.presentation.ui.items.itemlist.ItemListViewModel

sealed class ItemsNavigation : Route {
  object ItemList : ItemsNavigation() {
    override val route = "item_list"
  }

  object ItemDetails : ItemsNavigation() {
    const val itemIdArg = "itemId"
    override val route = "item_details/{${itemIdArg}}"

    fun createNavigationRoute(itemId: String): String = "item_details/${Uri.encode(itemId)}"
  }
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
    ) {
      val itemListViewModel = hiltViewModel<ItemListViewModel>()
      ItemListScreen(
        itemListViewModel = itemListViewModel,
        itemClicked = {
          //viewModel.setItem(item)
          navController.navigate(ItemsNavigation.ItemDetails.createNavigationRoute(it.itemID.toString())) {
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
      ItemsNavigation.ItemDetails.route,
      arguments = listOf(navArgument(ItemsNavigation.ItemDetails.itemIdArg) {
        type = NavType.StringType
      }),
      enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
      exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
    ) {
      val itemDetailsViewModel = hiltViewModel<ItemDetailsViewModel>()

      ItemDetailsScreen(
        itemDetailsViewModel = itemDetailsViewModel,
        itemClicked = {
          navController.navigate(ItemsNavigation.ItemDetails.createNavigationRoute(it.itemID.toString())) {
            popUpTo(ItemsNavigation.ItemList.route)
          }
        },
        modifier = Modifier.fillMaxSize().padding(16.dp)
      )
    }
  }
}