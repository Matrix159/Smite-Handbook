package com.matrix.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColorScheme(
//    primary = PrimaryDark,
//    primaryVariant = PrimaryVariantDark,
//    onPrimary = OnPrimaryDark,
//
//    secondary = SecondaryDark,
//    secondaryVariant = SecondaryVariantDark,
//    onSecondary = OnSecondaryDark,
//
//    background = BackgroundDark,
//    onBackground = OnBackgroundDark,
//
//    surface = SurfaceDark,
//    onSurface = OnSurfaceDark,
//
//    error = ErrorDark,
//    onError = OnErrorDark,
)

private val LightColorPalette = lightColorScheme(
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200,
//
//    /* Other default colors to override */
//    background = Color.White,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black,
)

@Composable
fun MaterializedSmiteTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {

  // Dynamic color is available on Android 12+
  val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
  val colorScheme = when {
    dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
    dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
    darkTheme -> DarkColorPalette
    else -> LightColorPalette
  }
  val systemUiController = rememberSystemUiController()
  if (darkTheme) {
    systemUiController.setSystemBarsColor(
      color = colorScheme.background
    )

  } else {
    systemUiController.setSystemBarsColor(
      color = colorScheme.background
    )
  }

  // This is to match the navigation bar used in the app, it matches the elevation of the bar
  systemUiController.setNavigationBarColor(
    color = colorScheme.surfaceColorAtElevation(3.dp),
  )

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    // Use default shapes currently
    //shapes = MaterialTheme.shapes,
    content = content
  )
}