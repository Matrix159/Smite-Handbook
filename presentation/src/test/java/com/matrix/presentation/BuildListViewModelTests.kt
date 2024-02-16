package com.matrix.presentation

import com.matrix.presentation.ui.builds.buildlist.BuildListUiState
import com.matrix.presentation.ui.builds.buildlist.BuildListViewModel
import com.matrix.shared.testing.builder.getMockBuildInformation
import com.matrix.shared.testing.builder.getMockGodInformation
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
class BuildListViewModelTests {
  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private lateinit var buildListViewModel: BuildListViewModel
  private lateinit var smiteRepo: FakeSmiteRepository

  @Before
  fun setup() {
    smiteRepo = FakeSmiteRepository()
    buildListViewModel = BuildListViewModel(smiteRepo)
  }

  @Test
  fun `Build list state is Loading by default`() = runTest {
    assertEquals(BuildListUiState.Loading, buildListViewModel.uiState.value)
  }

  @Test
  fun `Build list state is Success when builds exist`() = runTest {
    smiteRepo.addGods(listOf(getMockGodInformation(1)))
    smiteRepo.addGods(listOf(getMockGodInformation(2)))
    val expectedBuilds = listOf(
      getMockBuildInformation(1, 1, emptyList()),
      getMockBuildInformation(1, 2, emptyList()),
    )

    smiteRepo.addBuilds(expectedBuilds)
    val collectJob = launch(UnconfinedTestDispatcher()) { buildListViewModel.uiState.collect() }
    assertEquals(
      BuildListUiState.Success(builds = expectedBuilds),
      buildListViewModel.uiState.value
    )
    collectJob.cancel()
  }

  @Test
  fun `Build list state is Error when an exception happens`() = runTest {
    smiteRepo.shouldThrowError = true
    val collectJob = launch(UnconfinedTestDispatcher()) { buildListViewModel.uiState.collect() }
    assertEquals(BuildListUiState.Error, buildListViewModel.uiState.value)
    collectJob.cancel()
  }
}