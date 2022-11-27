package com.matrix.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.matrix.presentation.ui.theme.MaterializedSmiteTheme
import com.matrix.shared.Greeting
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Timber.i("Login Activity", "Hello from shared module: " + (Greeting().greeting()))
    WindowCompat.setDecorFitsSystemWindows(window, false)

    val test = OfflineFirstSmiteRepository()
    setContent {
      MaterializedSmiteTheme {
        SmiteApp()
      }
    }
  }
}