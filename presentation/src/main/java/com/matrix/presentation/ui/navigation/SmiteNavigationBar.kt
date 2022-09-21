package com.matrix.presentation.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.matrix.presentation.Screen

@Composable
fun SmiteNavigationBar(
  topLevelDestinations: List<Screen>,
  navController: NavController,
  modifier: Modifier = Modifier
) {
  NavigationBar(modifier = modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    topLevelDestinations.forEachIndexed { _, screen ->
      NavigationBarItem(
        icon = { Icon(painterResource(screen.iconResourceId), contentDescription = null) },
        label = { Text(stringResource(screen.resourceId)) },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
          val currentTab =
            currentDestination?.hierarchy?.any { it.route == screen.route } == true
          // If we're not already in this navigation graph
          if (!currentTab) {
            navController.navigate(screen.route) {
              // Pop up to the start destination of the graph to
              // avoid building up a large stack of destinations
              // on the back stack as users select items
              popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                // Do this to avoid double starting destinations on the stack
//                if (navController.graph.findStartDestination().hierarchy.any { it.route == screen.route }) {
//                  inclusive = true
//                }
              }
              launchSingleTop = true
              restoreState = true
            }
          }
        }
      )
    }
  }
}