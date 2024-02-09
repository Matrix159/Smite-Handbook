package com.matrix.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.matrix.presentation.ui.gods.godlist.GodListScreen
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import com.matrix.presentation.ui.theme.SmiteHandbookTheme
import com.matrix.shared.testing.builder.getMockGodInformation
import com.matrix.shared.testing.fakes.FakeSmiteRepository
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Roboelectric tests for the Gods Screen
 */
@RunWith(AndroidJUnit4::class)
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
      SmiteHandbookTheme {
        GodListScreen(GodListViewModel(smiteRepository, SavedStateHandle()), {
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

    composeTestRule.onNodeWithText("Search for a god").performTextInput(gods[0].name)
    composeTestRule.onNodeWithText(gods[1].name).assertDoesNotExist()
    assertTrue(firstGodClicked)
  }
}