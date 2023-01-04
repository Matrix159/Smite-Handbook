package com.matrix.shared.data

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
import com.matrix.shared.testing.builder.getMockGodInformation
import com.matrix.shared.testing.builder.getMockItemInformation
import com.matrix.shared.testing.fakes.FakePatchVersionDataSource
import com.matrix.shared.testing.fakes.FakeSmiteLocalDataSource
import com.matrix.shared.testing.fakes.FakeSmiteRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests the SmiteRepository implementation in the data layer
 */
@OptIn(ExperimentalCoroutinesApi::class)
internal class SmiteRepositoryTest {
  private lateinit var remoteDataSource: FakeSmiteRemoteDataSource
  private lateinit var localDataSource: FakeSmiteLocalDataSource
  private lateinit var patchVersionDataSource: FakePatchVersionDataSource

  private lateinit var repository: OfflineFirstSmiteRepository

  @BeforeTest
  fun before() {
    // Needed so that the Kermit logger doesn't try to use platform-dependent loggers
    Logger.setLogWriters(CommonWriter())
    remoteDataSource = FakeSmiteRemoteDataSource()
    localDataSource = FakeSmiteLocalDataSource()
    patchVersionDataSource = FakePatchVersionDataSource()
    repository = OfflineFirstSmiteRepository(remoteDataSource, localDataSource, patchVersionDataSource)
  }


  @Test
  fun `getGods should populate and return two gods after syncing`() = runTest {
    // verify local is empty before the retrieval
    assertTrue(localDataSource.getGods().first().isEmpty())
    repository.sync()
    val results = repository.getGods().first()
    // verify local was updated
    assertEquals(2, localDataSource.getGods().first().size)
    assertEquals(2, results.size)
  }

  @Test
  fun `Should return a new god list when patch changes`() = runTest {
    val firstPatch = "9.7"
    val secondPatch = "9.8"

    // Setup existing gods
    localDataSource.saveGods(listOf(getMockGodInformation(1), getMockGodInformation(2)))
    patchVersionDataSource.setPatchVersion(firstPatch)
    val firstGodList = repository.getGods().first()

    patchVersionDataSource.setPatchVersion(secondPatch)
    remoteDataSource.increaseReturnedGodsByOne()

    repository.sync()
    val secondGodList = repository.getGods().first()

    assertEquals(2, firstGodList.size)
    assertEquals(3, secondGodList.size)
  }

  @Test
  fun `Should return a new item list when patch changes`() = runTest {
    val firstPatch = "9.7"
    val secondPatch = "9.8"

    // Setup existing items
    localDataSource.saveItems(listOf(getMockItemInformation(1), getMockItemInformation(2)))
    patchVersionDataSource.setPatchVersion(firstPatch)
    val firstItemList = repository.getItems().first()

    patchVersionDataSource.setPatchVersion(secondPatch)
    remoteDataSource.increaseReturnedItemsByOne()

    repository.sync()
    val secondItemList = repository.getItems().first()

    assertEquals(2, firstItemList.size)
    assertEquals(3, secondItemList.size)
  }
}