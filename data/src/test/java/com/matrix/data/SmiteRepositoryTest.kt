package com.matrix.data

import com.matrix.data.local.LocalGodList
import com.matrix.data.local.LocalItemList
import com.matrix.data.local.interfaces.SmiteLocalDataSource
import com.matrix.data.model.PatchVersionInfo
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.data.repository.SmiteRepositoryImpl
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
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

  @InjectMockKs
  private lateinit var repository: SmiteRepositoryImpl

  @Before
  fun before() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    repository = SmiteRepositoryImpl(remoteDataSource, localDataSource)
  }

  // TODO: try a Fake version of this
  @Test
  fun `Should return a new god list when patch changes`() = runTest {
    val firstPatch = "9.7"
    val secondPatch = "9.8s"

    // Local has first patch data stored and remote is still first patch
    coEvery { remoteDataSource.getPatchVersion() } returns PatchVersionInfo(firstPatch)
    coEvery { localDataSource.readGods() } returns LocalGodList(
      SmiteRepositoryData.firstPatchList,
      firstPatch
    )
    val firstGodList = repository.getGods()

    // Patch changed from remote, so remote should return new data and we should be overriding the
    // updating the local source of truth
    coEvery { remoteDataSource.getPatchVersion() } returns PatchVersionInfo(secondPatch)
    coEvery { remoteDataSource.getGods() } returns SmiteRepositoryData.secondPatchList
    val secondGodList = repository.getGods()

    assertNotEquals(firstGodList.size, secondGodList.size)
  }
}