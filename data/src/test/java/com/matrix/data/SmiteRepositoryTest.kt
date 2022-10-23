package com.matrix.data

import com.matrix.data.builder.getMockGodEntity
import com.matrix.data.builder.getMockItemEntity
import com.matrix.data.fakes.PatchVersionDataSourceFake
import com.matrix.data.fakes.SmiteLocalDataSourceFake
import com.matrix.data.fakes.SmiteRemoteDataSourceFake
import com.matrix.data.repository.SmiteRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

  private lateinit var repository: SmiteRepositoryImpl

  @Before
  fun before() {
    remoteDataSource = SmiteRemoteDataSourceFake()
    localDataSource = SmiteLocalDataSourceFake()
    patchVersionDataSource = PatchVersionDataSourceFake()
    repository = SmiteRepositoryImpl(remoteDataSource, localDataSource, patchVersionDataSource)
  }


  @Test
  fun `getGods should populate and return two gods from local data when no local data is present`() = runTest {
    // verify local is empty before the retrieval
    assertTrue(localDataSource.readGods().isEmpty())
    val results = repository.getGods()
    // verify local was updated
    assertEquals(2, localDataSource.readGods().size)
    assertEquals(2, results.size)
  }

  @Test
  fun `Should return a new god list when patch changes`() = runTest {
    val firstPatch = "9.7"
    val secondPatch = "9.8"

    // Setup existing gods
    localDataSource.saveGods(listOf(getMockGodEntity(1), getMockGodEntity(2)))
    patchVersionDataSource.setPatchVersion(firstPatch)
    val firstGodList = repository.getGods()

    patchVersionDataSource.setPatchVersion(secondPatch)
    remoteDataSource.increaseReturnedGodsByOne()
    val secondGodList = repository.getGods()

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
    val firstItemList = repository.getItems()

    patchVersionDataSource.setPatchVersion(secondPatch)
    remoteDataSource.increaseReturnedItemsByOne()
    val secondItemList = repository.getItems()

    assertEquals(2, firstItemList.size)
    assertEquals(3, secondItemList.size)
  }
}