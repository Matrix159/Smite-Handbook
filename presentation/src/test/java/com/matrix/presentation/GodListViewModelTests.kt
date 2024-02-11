package com.matrix.presentation

import androidx.lifecycle.SavedStateHandle
import com.matrix.presentation.ui.gods.godlist.GodListUiState
import com.matrix.presentation.ui.gods.godlist.GodListViewModel
import com.matrix.shared.testing.builder.getMockGodInformation
import com.matrix.shared.testing.fakes.FakeSmiteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GodListViewModelTests {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var godListViewModel: GodListViewModel
  private lateinit var smiteRepo: FakeSmiteRepository

  @Before
  fun setup() {
    smiteRepo = FakeSmiteRepository()
    godListViewModel = GodListViewModel(smiteRepo, SavedStateHandle())
  }

  @Test
  fun `God list state is Loading by default`() = runTest {
    assertEquals(GodListUiState.Loading, godListViewModel.uiState.value)
  }

  @Test
  fun `God list state is Success when gods are empty`() = runTest {
    val collectJob = launch(UnconfinedTestDispatcher()) { godListViewModel.uiState.collect() }
    assertEquals(GodListUiState.Loading, godListViewModel.uiState.value)
    collectJob.cancel()
  }

  @Test
  fun `God list state is Success when gods exist`() = runTest {
    val collectJob = launch(UnconfinedTestDispatcher()) { godListViewModel.uiState.collect() }

    assertEquals(GodListUiState.Loading, godListViewModel.uiState.value)
    val expectedGods = listOf(getMockGodInformation(0), getMockGodInformation(1))
    smiteRepo.addGods(expectedGods)
    assertEquals(GodListUiState.Success(gods = expectedGods), godListViewModel.uiState.value)

    collectJob.cancel()
  }
}