package com.matrix.api

import com.matrix.api.impl.SmiteRepositoryImpl
import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodInformation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
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
  fun test_getGods_isEmpty() = runBlockingTest {
    val emptyGodList: List<GodInformation> = mutableListOf()
    assertEquals(emptyGodList, repository.getGods())
  }
}