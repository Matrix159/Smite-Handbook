package com.matrix.data

import com.matrix.data.builder.getMockGodEntity
import com.matrix.data.fakes.PatchVersionDataSourceFake
import com.matrix.data.fakes.SmiteLocalDataSourceFake
import com.matrix.data.fakes.SmiteRemoteDataSourceFake
import com.matrix.data.repository.SmiteRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
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

//  @InjectMockKs
  private lateinit var repository: SmiteRepositoryImpl

  @Before
  fun before() {
    remoteDataSource = SmiteRemoteDataSourceFake()
    localDataSource = SmiteLocalDataSourceFake()
    patchVersionDataSource = PatchVersionDataSourceFake()
    repository = SmiteRepositoryImpl(remoteDataSource, localDataSource, patchVersionDataSource)
  }

  // TODO: try a Fake version of this
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

    // TODO: Needs to be items
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
}