package com.matrix.presentation

import androidx.annotation.StringRes
import com.matrix.materializedsmite.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val iconResourceId: Int) {
  object Gods : Screen("gods", R.string.god_screen, R.drawable.gods)
  object Items : Screen("items", R.string.item_screen, R.drawable.item)
}
