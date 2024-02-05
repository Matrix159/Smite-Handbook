package com.matrix.presentation.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matrix.presentation.defaultAnimationSpec
import com.matrix.presentation.ui.gods.godlist.GodListScreen
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import com.matrix.shared.data.model.gods.GodInformation
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.godListRoute(
  route: String,
  godClicked: (godInformation: GodInformation) -> Unit,
) {
  composable(
    route,
    enterTransition = { fadeIn(animationSpec = defaultAnimationSpec) },
    exitTransition = { fadeOut(animationSpec = defaultAnimationSpec) }
  ) {
    val godListViewModel = koinViewModel<GodListViewModel>()

    GodListScreen(
      godListViewModel,
      godClicked = {
        godClicked(it)
      },
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .imePadding()
    )
  }
}