package com.matrix.presentation

import androidx.lifecycle.SavedStateHandle
import com.matrix.presentation.ui.items.itemdetails.ItemDetailUiState
import com.matrix.presentation.ui.items.itemdetails.ItemDetailsViewModel
import com.matrix.presentation.ui.items.navigation.ItemsNavigation
import com.matrix.presentation.utils.ItemNode
import com.matrix.shared.testing.builder.getMockItemInformation
import com.matrix.shared.testing.fakes.FakeSmiteRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class ItemDetailsViewModelTests {
  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var itemDetailsViewModel: ItemDetailsViewModel
  private lateinit var smiteRepo: FakeSmiteRepository
  private lateinit var savedStateHandle: SavedStateHandle

  @Before
  fun setup() {
    smiteRepo = FakeSmiteRepository()
    savedStateHandle = SavedStateHandle()
    savedStateHandle[ItemsNavigation.ItemDetails.itemIdArg] = "1"
    itemDetailsViewModel = ItemDetailsViewModel(smiteRepo, savedStateHandle)
  }

  @Test
  fun `Item details state is Loading by default`() = runTest {
    assertEquals(ItemDetailUiState.Loading, itemDetailsViewModel.uiState.value)
  }

  @Test
  fun `Items details state is Success when item exists`() = runTest {
    val expectedItem = getMockItemInformation(1)
    smiteRepo.addItems(listOf(expectedItem))
    val collectJob = launch(UnconfinedTestDispatcher()) { itemDetailsViewModel.uiState.collect() }
    assertEquals(ItemDetailUiState.Success(item = expectedItem, itemTreeNodes = listOf(ItemNode(expectedItem))), itemDetailsViewModel.uiState.value)
    collectJob.cancel()
  }

  @Test
  fun `Items details state is Error when an exception happens`() = runTest {
    smiteRepo.shouldThrowError = true
    val collectJob = launch(UnconfinedTestDispatcher()) { itemDetailsViewModel.uiState.collect() }
    assertEquals(ItemDetailUiState.Error, itemDetailsViewModel.uiState.value)
    collectJob.cancel()
  }

}
