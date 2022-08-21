package com.matrix.data.repository

import com.matrix.data.local.interfaces.SmiteLocalDataSource
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkin
import com.matrix.domain.models.Item
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SmiteRepositoryImpl @Inject constructor(
  private val networkDataSource: SmiteRemoteDataSource,
  private val localDataSource: SmiteLocalDataSource
) : SmiteRepository {

  override suspend fun getGods(refresh: Boolean): List<GodInformation> {
    return withContext(Dispatchers.IO) {
      val localGodData: List<GodInformation> = localDataSource.readGods()
      if (refresh || localGodData.isEmpty()) {
        val newData = networkDataSource.getGods()
        localDataSource.saveGods(newData)
        Timber.d("Saved new god data to local storage")
        return@withContext newData
      }
      localGodData
    }
  }

  override suspend fun getGodSkins(godId: Int): List<GodSkin> {
    try {
      return networkDataSource.getGodSkins(godId)
    } catch (ex: Exception) {
      if (ex !is CancellationException) {
        throw ex
      }
    }
    return emptyList()
  }

  override suspend fun getItems(refresh: Boolean): List<Item> {
    return withContext(Dispatchers.IO) {
      val localItemData: List<Item> = localDataSource.readItems()
      if (refresh || localItemData.isEmpty()) {
        val newData = networkDataSource.getItems()
        localDataSource.saveItems(newData)
        Timber.d("Saved new item data to local storage")
        return@withContext newData
      }
      localItemData
    }
  }
}