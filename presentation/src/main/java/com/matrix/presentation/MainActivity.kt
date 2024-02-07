package com.matrix.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.matrix.presentation.ui.theme.SmiteHandbookTheme
import com.matrix.shared.Greeting
import timber.log.Timber

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Timber.i("Login Activity", "Hello from shared module: " + (Greeting().greeting()))
    enableEdgeToEdge()

    setContent {
      SmiteHandbookTheme {
        SmiteApp()
      }
    }
  }
}