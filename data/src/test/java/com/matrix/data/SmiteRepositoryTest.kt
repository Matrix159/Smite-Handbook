package com.matrix.data

import com.matrix.data.local.LocalGodList
import com.matrix.data.local.interfaces.DataStoreSource
import com.matrix.data.local.interfaces.SmiteLocalDataSource
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.data.repository.SmiteRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Tests the SmiteRepository implementation in the data layer
 */
@OptIn(ExperimentalCoroutinesApi::class)
class SmiteRepositoryTest {
  @MockK
  private lateinit var remoteDataSource: SmiteRemoteDataSource

  @MockK
  private lateinit var localDataSource: SmiteLocalDataSource

  @MockK
  private lateinit var sharedPrefsDataSource: DataStoreSource

  @InjectMockKs
  private lateinit var repository: SmiteRepositoryImpl

  @Before
  fun before() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    repository = SmiteRepositoryImpl(remoteDataSource, localDataSource, sharedPrefsDataSource)
  }

  // TODO: try a Fake version of this
  @Test
  fun `Should return a new god list when patch changes`() = runTest {
    val firstPatch = "9.7"
    val secondPatch = "9.8"

    // Local data source should be locked in for this test
    coEvery { localDataSource.readGods() } returns LocalGodList(
      SmiteRepositoryData.firstPatchList,
      firstPatch
    )

    // The current patch is the first patch
    coEvery { sharedPrefsDataSource.getPatchVersion() } returns firstPatch
    val firstGodList = repository.getGods()

    // Patch changed, so remote should return new data and we should be overriding the
    // updating the local source of truth
    coEvery { sharedPrefsDataSource.getPatchVersion() } returns secondPatch
    coEvery { remoteDataSource.getGods() } returns SmiteRepositoryData.secondPatchList
    val secondGodList = repository.getGods()

    assertNotEquals(firstGodList.size, secondGodList.size)
  }
}