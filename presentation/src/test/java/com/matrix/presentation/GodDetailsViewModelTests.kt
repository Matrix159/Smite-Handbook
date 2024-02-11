package com.matrix.presentation

import androidx.lifecycle.SavedStateHandle
import com.matrix.presentation.ui.gods.goddetails.GodDetailsUiState
import com.matrix.presentation.ui.gods.goddetails.GodDetailsViewModel
import com.matrix.presentation.ui.gods.navigation.GodsNavigation
import com.matrix.shared.testing.builder.getMockGodInformation
import com.matrix.shared.testing.builder.getMockGodSkinInformation
import com.matrix.shared.testing.fakes.FakeSmiteRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class GodDetailsViewModelTests {
  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var godDetailsViewModel: GodDetailsViewModel
  private lateinit var smiteRepo: FakeSmiteRepository
  private lateinit var savedStateHandle: SavedStateHandle

  @Before
  fun setup() {
    smiteRepo = FakeSmiteRepository()
    savedStateHandle = SavedStateHandle()
    savedStateHandle[GodsNavigation.GodDetails.godIdArg] = "1"
    godDetailsViewModel = GodDetailsViewModel(smiteRepo, savedStateHandle)
  }

  @Test
  fun `God details state is Loading by default`() = runTest {
    assertEquals(GodDetailsUiState.Loading, godDetailsViewModel.uiState.value)
  }

  @Test
  fun `God details state is Success when god exists and no skins exist`() = runTest {
    val expectedGod = getMockGodInformation(1)
    smiteRepo.addGods(listOf(expectedGod))
    val collectJob = launch(UnconfinedTestDispatcher()) { godDetailsViewModel.uiState.collect() }
    assertEquals(GodDetailsUiState.Success(godInformation = expectedGod, skins = emptyList()), godDetailsViewModel.uiState.value)
    collectJob.cancel()
  }

  @Test
  fun `God details state contains skins when they exist`() = runTest {
    val expectedGod = getMockGodInformation(1)
    val expectedSkins = listOf(
      getMockGodSkinInformation(1, "Bob"),
      getMockGodSkinInformation(2, "Joe")
    )
    smiteRepo.addGods(listOf(expectedGod))
    smiteRepo.addSkins(expectedSkins)
    val collectJob = launch(UnconfinedTestDispatcher()) { godDetailsViewModel.uiState.collect() }
    assertEquals(GodDetailsUiState.Success(godInformation = expectedGod, skins = expectedSkins), godDetailsViewModel.uiState.value)
    collectJob.cancel()
  }
}
