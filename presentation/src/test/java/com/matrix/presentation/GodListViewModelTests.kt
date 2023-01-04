package com.matrix.presentation

import com.matrix.presentation.ui.gods.godlist.GodListUiState
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import com.matrix.shared.testing.builder.getMockGodInformation
import com.matrix.shared.testing.fakes.FakeSmiteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.test.assertEquals

/**
 * A JUnit [TestRule] that sets the Main dispatcher to [testDispatcher]
 * for the duration of the test.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
  val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
  override fun starting(description: Description) {
    Dispatchers.setMain(testDispatcher)
  }

  override fun finished(description: Description) {
    Dispatchers.resetMain()
  }
}

@OptIn(ExperimentalCoroutinesApi::class)
class GodListViewModelTests {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var godListViewModel: GodListViewModel
  private val smiteRepo = FakeSmiteRepository()

  @Test
  fun testGodListViewModelWithData() = runTest {
    godListViewModel = GodListViewModel(smiteRepo)
    val collectJob = launch(UnconfinedTestDispatcher()) { godListViewModel.uiState.collect() }

    assertEquals(GodListUiState.Loading, godListViewModel.uiState.value)
    val expectedGods = listOf(getMockGodInformation(0), getMockGodInformation(1))
    smiteRepo.addGods(expectedGods)
    assertEquals(GodListUiState.Success(gods = expectedGods), godListViewModel.uiState.value)

    collectJob.cancel()
  }
}