package com.matrix.presentation

import androidx.lifecycle.SavedStateHandle
import com.matrix.presentation.ui.items.itemlist.ItemListUiState
import com.matrix.presentation.ui.items.itemlist.ItemListViewModel
import com.matrix.shared.testing.builder.getMockItemInformation
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
class ItemListViewModelTests {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var itemListViewModel: ItemListViewModel
  private lateinit var smiteRepo: FakeSmiteRepository

  @Before
  fun setup() {
    smiteRepo = FakeSmiteRepository()
    itemListViewModel = ItemListViewModel(smiteRepo, SavedStateHandle())
  }

  @Test
  fun `Item list state is Loading by default`() = runTest {
    assertEquals(ItemListUiState.Loading, itemListViewModel.uiState.value)
  }

  @Test
  fun `Item list state is Loading when items are empty`() = runTest {
    val collectJob = launch(UnconfinedTestDispatcher()) { itemListViewModel.uiState.collect() }
    assertEquals(ItemListUiState.Loading, itemListViewModel.uiState.value)
    collectJob.cancel()
  }

  @Test
  fun `Item list state is Success when items exist`() = runTest {
    val collectJob = launch(UnconfinedTestDispatcher()) { itemListViewModel.uiState.collect() }

    assertEquals(ItemListUiState.Loading, itemListViewModel.uiState.value)
    val expectedItems = listOf(getMockItemInformation(0), getMockItemInformation(1))
    smiteRepo.addItems(expectedItems)
    assertEquals(ItemListUiState.Success(items = expectedItems), itemListViewModel.uiState.value)

    collectJob.cancel()
  }
}