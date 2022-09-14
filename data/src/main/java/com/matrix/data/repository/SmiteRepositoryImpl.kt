package com.matrix.data.repository

import com.matrix.data.local.db.entity.BuildEntity
import com.matrix.data.local.db.entity.GodEntity
import com.matrix.data.local.db.entity.ItemEntity
import com.matrix.data.local.interfaces.PatchVersionDataSource
import com.matrix.data.local.interfaces.SmiteLocalDataSource
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.domain.contracts.SmiteRepository
import com.matrix.domain.models.BuildInformation
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.GodSkinInformation
import com.matrix.domain.models.ItemInformation
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SmiteRepositoryImpl @Inject constructor(
  private val networkDataSource: SmiteRemoteDataSource,
  private val localDataSource: SmiteLocalDataSource,
  private val sharedPrefsDataSource: PatchVersionDataSource
) : SmiteRepository {

  override suspend fun getGods(refresh: Boolean): List<GodInformation> {
    return withContext(Dispatchers.IO) {
      val currentPatchVersion: String? = sharedPrefsDataSource.getPatchVersion().firstOrNull()
      val localGods: List<GodEntity> = localDataSource.readGods()
      // Determine if we need to fetch from remote, we use patch version as our way of syncing data
      if (localGods.isEmpty() || localGods.any { it.patchVersion != currentPatchVersion } || refresh) {
        val newData = networkDataSource.getGods()
        localDataSource.saveGods(newData.map {
          GodEntity.fromApi(it, patchVersion = currentPatchVersion)
        })
        Timber.d("Saved new god data to local storage")
      }
      localDataSource.readGods().map { it.toDomain() }
    }
  }

  override suspend fun getGodSkins(godId: Int): List<GodSkinInformation> {
    try {
      return networkDataSource.getGodSkins(godId).map { it.toDomain() }
    } catch (ex: Exception) {
      if (ex !is CancellationException) {
        throw ex
      }
    }
    return emptyList()
  }

  override suspend fun getItems(refresh: Boolean): List<ItemInformation> {
    return withContext(Dispatchers.IO) {
      val currentPatchVersion: String? = sharedPrefsDataSource.getPatchVersion().firstOrNull()
      val localItems: List<ItemEntity> = localDataSource.readItems()
      // Determine if we need to fetch from remote, we use patch version as our way of syncing data
      if (localItems.isEmpty() || localItems.any { it.patchVersion != currentPatchVersion } || refresh) {
        val newData = networkDataSource.getItems()
        localDataSource.saveItems(newData.map {
          ItemEntity.fromApi(it, patchVersion = currentPatchVersion)
        })
        Timber.d("Saved new item data to local storage")
      }
      localDataSource.readItems().map { it.toDomain() }
    }
  }

  override fun getBuilds(): Flow<List<BuildInformation>> =
    localDataSource.getBuilds().map { list -> list.map { it.toDomain() } }

  override suspend fun createBuild(buildInformation: BuildInformation) {
    localDataSource.createBuild(
      buildEntity = BuildEntity(godId = buildInformation.god.id),
      itemIds = buildInformation.items.map { it.itemID })
  }

  override suspend fun deleteBuild(buildInformation: BuildInformation) {
    localDataSource.deleteBuild(
      BuildEntity(
        id = buildInformation.id,
        godId = buildInformation.god.id
      )
    )
  }

  /**
   * Grabs the latest patch version from the remote data source and stores it in shared prefs data source
   */
  override suspend fun syncPatchVersion() {
    sharedPrefsDataSource.setPatchVersion(networkDataSource.getPatchVersion().version)
  }
}