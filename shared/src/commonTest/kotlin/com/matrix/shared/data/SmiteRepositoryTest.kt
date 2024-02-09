package com.matrix.shared.data

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
import com.matrix.shared.testing.builder.getMockBuildInformation
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
import kotlin.test.assertFails
import kotlin.test.assertTrue

/**
 * Tests the SmiteRepository implementation in the data layer
 */
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


  // <editor-fold desc="getGods"/>
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
  fun `getGods should return a new god list when patch changes`() = runTest {
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
  fun `getGods should return an empty list when no data exists`() = runTest {
    val results = repository.getGods().first()
    assertTrue(results.isEmpty())
  }
  // </editor-fold>

  // <editor-fold desc="getGod">
  @Test
  fun `getGod returns result for valid ID`() = runTest {
    val id = 1L
    localDataSource.saveGods(listOf(getMockGodInformation(id)))
    val godResult = repository.getGod(id).first()
    assertEquals(id, godResult.id)
  }

  @Test
  fun `getGod throws error for invalid ID`() = runTest {
    val id = -1L
    assertFails { repository.getGod(id).first() }
  }
  // </editor-fold>

  // <editor-fold desc="getGodSkins">
  @Test
  fun `getGodSkins returns two results for ID - 1`() = runTest {
    val id = 1L
    val results = repository.getGodSkins(id).first()
    assertEquals(2, results.size)
  }

  @Test
  fun `getGodSkins returns empty list for nonexistent ID`() = runTest {
    val id = -1L
    val results = repository.getGodSkins(id).first()
    assertTrue(results.isEmpty())
  }
  // </editor-fold>


  // <editor-fold desc="getItems">
  @Test
  fun `getItems should populate and return two items after syncing`() = runTest {
    // verify local is empty before the retrieval
    assertTrue(localDataSource.getItems().first().isEmpty())
    repository.sync()
    val results = repository.getItems().first()
    // verify local was updated
    assertEquals(2, localDataSource.getItems().first().size)
    assertEquals(2, results.size)
  }

  @Test
  fun `getItems should return a new item list when patch changes`() = runTest {
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

  @Test
  fun `getItems should return an empty list when no data exists`() = runTest {
    val results = repository.getItems().first()
    assertTrue(results.isEmpty())
  }
  // </editor-fold>


  // <editor-fold desc="getItem">
  @Test
  fun `getItem returns result for valid ID`() = runTest {
    val id = 1L
    localDataSource.saveItems(listOf(getMockItemInformation(id)))
    val itemResult = repository.getItem(id).first()
    assertEquals(id, itemResult.itemID)
  }

  @Test
  fun `getItem throws error for invalid ID`() = runTest {
    val id = -1L
    assertFails { repository.getItem(id).first() }
  }
  // </editor-fold>


  // <editor-fold desc="getBuilds">
  @Test
  fun `getBuilds returns results`() = runTest {
    localDataSource.saveBuild(getMockBuildInformation(0, 0, listOf(0, 1)))
    val buildResults = repository.getBuilds().first()
    assertTrue(buildResults.isNotEmpty())
  }

  @Test
  fun `getBuilds returns empty results`() = runTest {
    val buildResults = repository.getBuilds().first()
    assertTrue(buildResults.isEmpty())
  }
  // </editor-fold>


  // <editor-fold desc="getBuild">
  @Test
  fun `getBuild returns result for valid ID`() = runTest {
    val id = 1L
    localDataSource.saveBuild(getMockBuildInformation(id, 0, listOf(0, 1)))
    val build = repository.getBuild(id).first()
    assertEquals(id, build.id)
  }

  @Test
  fun `getBuild throws exception for invalid ID`() = runTest {
    assertFails { repository.getBuild(-1L) }
  }
  // </ editor-fold>

  @Test
  fun `saveBuild successfully creates a build`() = runTest {
    val id = 1L
    val build = getMockBuildInformation(id, 0, listOf(0, 1))
    repository.saveBuild(build)
    assertEquals(build, repository.getBuild(id).first())
  }

  @Test
  fun `updateGodInBuild successfully updates a build`() = runTest {
    val buildId = 1L
    val godId = 1L
    val newGodId = 2L
    val god = getMockGodInformation(godId)
    val newGod = getMockGodInformation(newGodId)
    val build = getMockBuildInformation(buildId, godId, emptyList())
    localDataSource.saveGods(listOf(god, newGod))
    localDataSource.saveBuild(build)
    repository.updateGodInBuild(buildId, newGodId)
    assertEquals(newGodId, repository.getBuild(buildId).first().god.id)
  }

  @Test
  fun `updateItemsInBuild successfully updates a build`() = runTest {
    val buildId = 1L
    val item1 = getMockItemInformation(1)
    val item2 = getMockItemInformation(2)
    val build = getMockBuildInformation(buildId, 1, listOf(1))
    localDataSource.saveItems(listOf(item1, item2))
    localDataSource.saveBuild(build)
    val newItemIds = listOf(2L)
    repository.updateItemsInBuild(buildId, newItemIds)
    assertEquals(newItemIds, repository.getBuild(buildId).first().items.map { it.itemID })
  }

  @Test
  fun `deleteBuild successfully deletes a build`() = runTest {
    val id = 1L
    val build = getMockBuildInformation(id, 0, listOf(0, 1))
    localDataSource.saveBuild(build)
    repository.deleteBuild(build)
    assertFails { repository.getBuild(id) }
  }

  @Test
  fun `sync updates data from remote data source into local data source`() = runTest {
    remoteDataSource.increaseReturnedGodsByOne()
    remoteDataSource.increaseReturnedItemsByOne()
    assertTrue { repository.getGods().first().isEmpty() }
    assertTrue { repository.getItems().first().isEmpty() }
    repository.sync()
    assertTrue { repository.getGods().first().isNotEmpty() }
    assertTrue { repository.getItems().first().isNotEmpty() }
  }
}