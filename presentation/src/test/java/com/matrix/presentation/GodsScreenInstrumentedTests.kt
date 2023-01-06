package com.matrix.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.matrix.presentation.ui.gods.godlist.GodListScreen
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import com.matrix.presentation.ui.theme.MaterializedSmiteTheme
import com.matrix.shared.testing.builder.getMockGodInformation
import com.matrix.shared.testing.fakes.FakeSmiteRepository
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Roboelectric tests for the Gods Screen
 */
@RunWith(RobolectricTestRunner::class)
class GodsScreenInstrumentedTests {

  @get:Rule
  val composeTestRule = createComposeRule()
  private lateinit var smiteRepository: FakeSmiteRepository

  @BeforeTest
  fun setup() {
    smiteRepository = FakeSmiteRepository()
  }

  @Test
  fun godListScreenTest()  {
    // Start the app
    var firstGodClicked = false
    composeTestRule.setContent {
      MaterializedSmiteTheme {
        GodListScreen(GodListViewModel(smiteRepository), {
          firstGodClicked = true
        })
      }
    }

    val gods = listOf(getMockGodInformation(0), getMockGodInformation(1))

    composeTestRule.onRoot().printToLog("beforeGods")
    composeTestRule.onNodeWithContentDescription("Loading animation").assertExists()
    smiteRepository.addGods(gods)
    composeTestRule.onNodeWithText(gods[0].name).assertIsDisplayed()
    composeTestRule.onNodeWithText(gods[1].name).assertIsDisplayed()
    composeTestRule.onRoot().printToLog("afterGods")

    composeTestRule.onNodeWithText(gods[0].name).performClick()
    assertTrue(firstGodClicked)
  }
}