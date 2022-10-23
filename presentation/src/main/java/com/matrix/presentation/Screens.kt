package com.matrix.presentation

import androidx.annotation.StringRes

sealed class Screen(
  val route: String,
  @StringRes val resourceId: Int,
  val iconResourceId: Int
) {
  object Gods : Screen("gods_screen", R.string.god_screen, R.drawable.gods)
  object Items : Screen("items_screen", R.string.item_screen, R.drawable.item)
  object Builds : Screen("builds_screen", R.string.build_screen, R.drawable.armor)
}
