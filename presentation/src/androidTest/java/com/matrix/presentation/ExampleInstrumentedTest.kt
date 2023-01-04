package com.matrix.presentation

import android.util.Log
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.matrix.presentation.fakes.FakeSmiteRepository
import com.matrix.presentation.injection.presentationKoinModule
import com.matrix.presentation.ui.gods.godlist.GodListScreen
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import com.matrix.presentation.ui.theme.MaterializedSmiteTheme
import com.matrix.shared.di.initKoin
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun myTest() = runTest {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    val koinApplication = initKoin {
      androidContext(appContext)
      androidLogger()
      modules(presentationKoinModule())
    }
    // Start the app
    composeTestRule.setContent {
      MaterializedSmiteTheme {
        GodListScreen(GodListViewModel(FakeSmiteRepository()), {
         Log.i("myTest", "God clicked")
        })
      }
    }
    composeTestRule.waitUntil(20000) {
      composeTestRule
        .onAllNodesWithText("Achilles")
        .fetchSemanticsNodes().size == 1
    }
    composeTestRule.onRoot().printToLog("currentLabelExists")


    composeTestRule.onNodeWithText("Search for a god").performClick()

    println(composeTestRule.mainClock.currentTime)
    //composeTestRule.onNodeWithText("Aegis Amulet").assertIsDisplayed()
  }

//  @Test
//  fun useAppContext() {
//    // Context of the app under test.
//    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//    assertEquals("com.matrix.presentation.test", appContext.packageName)
//  }
}