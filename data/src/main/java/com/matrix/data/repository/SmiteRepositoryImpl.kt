package com.matrix.data.repository

import com.matrix.data.local.LocalGodList
import com.matrix.data.local.LocalItemList
import com.matrix.data.local.interfaces.SharedPrefsDataSource
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
  private val localDataSource: SmiteLocalDataSource,
  private val sharedPrefsDataSource: SharedPrefsDataSource
) : SmiteRepository {

  // TODO: Write some tests around these functions and their syncing mechanisms
  override suspend fun getGods(refresh: Boolean): List<GodInformation> {
    return withContext(Dispatchers.IO) {
      val currentPatchVersion: String? = sharedPrefsDataSource.getPatchVersion()
      val localGodData: LocalGodList? = localDataSource.readGods()
      // Determine if we need to fetch from remote, we use patch version as our way of syncing data
      if (localGodData == null || localGodData.patchVersion != currentPatchVersion || refresh) {
        val newData = networkDataSource.getGods()
        localDataSource.saveGods(newData, currentPatchVersion)
        Timber.d("Saved new god data to local storage")
        return@withContext newData
      }
      localGodData.gods
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
      val currentPatchVersion: String? = sharedPrefsDataSource.getPatchVersion()
      val localItemData: LocalItemList? = localDataSource.readItems()
      if (localItemData == null || localItemData.patchVersion != currentPatchVersion || refresh) {
        val newData = networkDataSource.getItems()
        localDataSource.saveItems(newData, currentPatchVersion)
        Timber.d("Saved new item data to local storage")
        return@withContext newData
      }
      localItemData.items
    }
  }

  override suspend fun syncPatchVersion() {
    sharedPrefsDataSource.setPatchVersion(networkDataSource.getPatchVersion().version)
  }

}