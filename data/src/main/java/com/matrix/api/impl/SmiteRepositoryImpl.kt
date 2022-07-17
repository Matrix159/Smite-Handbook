package com.matrix.api.impl

import com.matrix.api.SmiteRemoteDataSource
import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class SmiteRepositoryImpl @Inject constructor(
  // TODO: Make a cached local storage data source
  private val smiteApi: SmiteRemoteDataSource
): SmiteRepository {

  private val cachedGodsMutex = Mutex()
  private var cachedGods = listOf<GodInformation>()

  private val cachedItemMutex = Mutex()
  private var cachedItems = listOf<Item>()

  override suspend fun getGods(refresh: Boolean): List<GodInformation> {
    try {
      if (refresh || cachedGods.isEmpty()) {
        cachedGodsMutex.withLock {
          this.cachedGods = smiteApi.getGods()
        }
      }

      return cachedGodsMutex.withLock { this.cachedGods }
    } catch (ex: Exception) {
      throw ex
    }
  }

  override suspend fun getGodSkins(godId: Int): List<GodSkin> {
    try {
      return smiteApi.getGodSkins(godId)
    } catch (ex: Exception) {
      throw ex
    }
  }

  override suspend fun getItems(refresh: Boolean): List<Item> {
    try {
      if (refresh || cachedItems.isEmpty()) {
        cachedItemMutex.withLock {
          this.cachedItems = smiteApi.getItems()
        }
      }

      return cachedItemMutex.withLock { this.cachedItems }
    } catch (ex: Exception) {
      throw ex
    }
  }
}