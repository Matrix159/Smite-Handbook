package com.matrix.presentation

import androidx.lifecycle.SavedStateHandle
import com.matrix.presentation.ui.builds.builddetails.BuildDetailsUiState
import com.matrix.presentation.ui.builds.builddetails.BuildDetailsViewModel
import com.matrix.presentation.ui.builds.navigation.BuildsNavigation
import com.matrix.shared.testing.builder.getMockBuildInformation
import com.matrix.shared.testing.builder.getMockGodInformation
import com.matrix.shared.testing.builder.getMockItemInformation
import com.matrix.shared.testing.fakes.FakeSmiteRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs


@OptIn(ExperimentalCoroutinesApi::class)
class BuildDetailsViewModelTests {
  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var buildDetailsViewModel: BuildDetailsViewModel
  private lateinit var smiteRepo: FakeSmiteRepository
  private lateinit var savedStateHandle: SavedStateHandle
  private val selectedGodId = MutableStateFlow<Long?>(null)
  private val selectedItemIds = MutableStateFlow(emptyList<Long>())
  @Before
  fun setup() {
    smiteRepo = FakeSmiteRepository()
    savedStateHandle = SavedStateHandle()
    savedStateHandle[BuildsNavigation.BuildDetails.buildIdArg] = "1"
    buildDetailsViewModel = BuildDetailsViewModel(smiteRepo, savedStateHandle, selectedGodId, selectedItemIds)
  }

  @Test
  fun `Build details state is Loading by default`() = runTest {
    assertEquals(BuildDetailsUiState.Loading, buildDetailsViewModel.uiState.value)
  }

  @Test
  fun `Build details state is Success when build exists`() = runTest {
    val mockGod = getMockGodInformation(1)
    val mockItem = getMockItemInformation(1)
    val expectedBuild = getMockBuildInformation(1, 1, listOf(1))
    smiteRepo.addGods(listOf(mockGod))
    smiteRepo.addItems(listOf(mockItem))
    smiteRepo.addBuilds(listOf(expectedBuild))
    val collectJob = launch(UnconfinedTestDispatcher()) { buildDetailsViewModel.uiState.collect() }
    assertEquals(
      BuildDetailsUiState.Success(
        buildInformation = expectedBuild,
        allGods = listOf(mockGod),
        allItems = listOf(mockItem),
      ), buildDetailsViewModel.uiState.value
    )

    collectJob.cancel()
  }

  @Test
  fun `Build details state is Error when build does not exist`() = runTest {
    savedStateHandle[BuildsNavigation.BuildDetails.buildIdArg] = "000"
    buildDetailsViewModel = BuildDetailsViewModel(smiteRepo, savedStateHandle, flowOf(null), flowOf(emptyList()))
    val collectJob = launch(UnconfinedTestDispatcher()) { buildDetailsViewModel.uiState.collect() }
    assertEquals(BuildDetailsUiState.Error, buildDetailsViewModel.uiState.value)
    collectJob.cancel()
  }

  @Test
  fun `deleteBuild removes build`() = runTest {
    val mockGod = getMockGodInformation(1)
    val mockItem = getMockItemInformation(1)
    val expectedBuild = getMockBuildInformation(1, 1, listOf(1))
    smiteRepo.addGods(listOf(mockGod))
    smiteRepo.addItems(listOf(mockItem))
    smiteRepo.addBuilds(listOf(expectedBuild))
    buildDetailsViewModel.deleteBuild(expectedBuild)
    assertEquals(0, smiteRepo.getBuilds().first().size)
  }

  @Test
  fun `saveBuild adds build`() = runTest {
    val mockGod = getMockGodInformation(1)
    val mockItem = getMockItemInformation(1)
    val expectedBuild = getMockBuildInformation(1, 1, listOf(1))
    smiteRepo.addGods(listOf(mockGod))
    smiteRepo.addItems(listOf(mockItem))
    buildDetailsViewModel.saveBuild(expectedBuild)
    assertEquals(1, smiteRepo.getBuilds().first().size)
  }

  @Test
  fun `god is updated in build when selectedGodId updates`() = runTest {
    val mockGod = getMockGodInformation(1)
    val mockGod2 = getMockGodInformation(2)
    val mockItem = getMockItemInformation(1)
    val expectedBuild = getMockBuildInformation(1, 1, listOf(1))
    val allGods = listOf(mockGod, mockGod2)
    val allItems = listOf(mockItem)
    smiteRepo.addGods(allGods)
    smiteRepo.addItems(allItems)
    smiteRepo.addBuilds(listOf(expectedBuild))
    val collectJob = launch(UnconfinedTestDispatcher()) { buildDetailsViewModel.uiState.collect() }
    assertEquals(
      BuildDetailsUiState.Success(
        buildInformation = expectedBuild,
        allGods = listOf(mockGod, mockGod2),
        allItems = listOf(mockItem),
      ), buildDetailsViewModel.uiState.value
    )
    selectedGodId.value = 2
    assertEquals(
      BuildDetailsUiState.Success(
        buildInformation = expectedBuild.copy(god = mockGod2),
        allGods = allGods,
        allItems = allItems,
      ), buildDetailsViewModel.uiState.value
    )
    collectJob.cancel()
  }

  @Test
  fun `items are updated in build when selectedItemIds updates`() = runTest {
    val mockGod = getMockGodInformation(1)
    val mockItem = getMockItemInformation(1)
    val mockItem2 = getMockItemInformation(2)
    val expectedBuild = getMockBuildInformation(1, 1, listOf(1))
    val allGods = listOf(mockGod)
    val allItems = listOf(mockItem, mockItem2)
    smiteRepo.addGods(allGods)
    smiteRepo.addItems(allItems)
    smiteRepo.addBuilds(listOf(expectedBuild))
    val collectJob = launch(UnconfinedTestDispatcher()) { buildDetailsViewModel.uiState.collect() }
    assertEquals(
      BuildDetailsUiState.Success(
        buildInformation = expectedBuild,
        allGods = listOf(mockGod),
        allItems = listOf(mockItem, mockItem2),
      ), buildDetailsViewModel.uiState.value
    )
    selectedItemIds.value = listOf(2)
    assertEquals(
      BuildDetailsUiState.Success(
        buildInformation = expectedBuild.copy(items = listOf(mockItem2)),
        allGods = allGods,
        allItems = allItems,
      ), buildDetailsViewModel.uiState.value
    )
    collectJob.cancel()
  }
}
