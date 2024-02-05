package com.matrix.presentation

import androidx.annotation.StringRes

sealed class Screen(
  val route: String,
  @StringRes val resourceId: Int,
  val iconResourceId: Int
) {
  data object Gods : Screen("gods_screen", R.string.god_screen, R.drawable.gods)
  data object Items : Screen("items_screen", R.string.item_screen, R.drawable.item)
  data object Builds : Screen("builds_screen", R.string.build_screen, R.drawable.armor)
}
