package com.matrix.data

import com.matrix.data.builder.getMockGodEntity
import com.matrix.data.builder.getMockItemEntity
import com.matrix.data.fakes.PatchVersionDataSourceFake
import com.matrix.data.fakes.SmiteLocalDataSourceFake
import com.matrix.data.fakes.SmiteRemoteDataSourceFake
import com.matrix.data.repository.OfflineFirstSmiteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Tests the SmiteRepository implementation in the data layer
 */
@OptIn(ExperimentalCoroutinesApi::class)
class SmiteRepositoryTest {
  private lateinit var remoteDataSource: SmiteRemoteDataSourceFake
  private lateinit var localDataSource: SmiteLocalDataSourceFake
  private lateinit var patchVersionDataSource: PatchVersionDataSourceFake

  private lateinit var repository: OfflineFirstSmiteRepository

  @Before
  fun before() {
    remoteDataSource = SmiteRemoteDataSourceFake()
    localDataSource = SmiteLocalDataSourceFake()
    patchVersionDataSource = PatchVersionDataSourceFake()
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
    localDataSource.saveGods(listOf(getMockGodEntity(1), getMockGodEntity(2)))
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
    localDataSource.saveItems(listOf(getMockItemEntity(1), getMockItemEntity(2)))
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