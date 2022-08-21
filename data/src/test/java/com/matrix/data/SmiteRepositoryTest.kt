package com.matrix.data

import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.data.repository.SmiteRepositoryImpl
import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

/**
 * Tests the SmiteRepository implementation in the data layer
 */
@OptIn(ExperimentalCoroutinesApi::class)
class SmiteRepositoryTest {

  private lateinit var repository: SmiteRepository

  @Before
  fun before() {
    val dataSourceMock = mock<SmiteRemoteDataSource> {
      onBlocking { getGods() } doReturn mutableListOf()
      onBlocking { getGodSkins(anyInt()) } doReturn mutableListOf()
      onBlocking { getItems() } doReturn mutableListOf()
    }
    repository = SmiteRepositoryImpl(dataSourceMock)
  }

  @Test
  fun test_getGods_isEmpty() = runTest {
    val emptyGodList: List<GodInformation> = mutableListOf()

    assertEquals(emptyGodList, repository.getGods())
  }

  @Test
  fun test_getGodSkins_isEmpty() = runTest {
    val emptyGodSkins: List<GodSkin> = mutableListOf()
    assertEquals(emptyGodSkins, repository.getGodSkins(0))
  }

  @Test
  fun test_getItems_isEmpty() = runTest {
    val emptyItems: List<Item> = mutableListOf()
    assertEquals(emptyItems, repository.getItems())
  }
}