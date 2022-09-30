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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class OfflineFirstSmiteRepository @Inject constructor(
  private val networkDataSource: SmiteRemoteDataSource,
  private val localDataSource: SmiteLocalDataSource,
  private val patchVersionDataSource: PatchVersionDataSource
) : SmiteRepository {

  override fun getGods(refresh: Boolean): Flow<List<GodInformation>> =
    localDataSource.getGods().map { entityList -> entityList.map { it.toDomain() } }

  override fun getGod(godId: Int): Flow<GodInformation> =
    localDataSource.getGod(godId).map { it.toDomain() }


  override suspend fun syncGods() = withContext(Dispatchers.IO) {
    val currentPatchVersion: String? = patchVersionDataSource.getPatchVersion().firstOrNull()
    val newData = networkDataSource.getGods()
    localDataSource.saveGods(newData.map {
      GodEntity.fromApi(it, patchVersion = currentPatchVersion)
    })
    Timber.d("Saved new god data to local storage")
  }

  override fun getGodSkins(godId: Int): Flow<List<GodSkinInformation>> =
    flow { emit(networkDataSource.getGodSkins(godId).map { it.toDomain() }) }

  override fun getItems(refresh: Boolean): Flow<List<ItemInformation>> =
    localDataSource.getItems().map { entityList -> entityList.map { it.toDomain() } }

  override fun getItem(itemId: Int): Flow<ItemInformation> =
    localDataSource.getItem(itemId).map { it.toDomain() }


  override suspend fun syncItems() = withContext(Dispatchers.IO) {
    val currentPatchVersion: String? = patchVersionDataSource.getPatchVersion().firstOrNull()
    val newData = networkDataSource.getItems()
    localDataSource.saveItems(newData.map {
      ItemEntity.fromApi(it, patchVersion = currentPatchVersion)
    })
    Timber.d("Saved new item data to local storage")
  }

  override fun getBuilds(): Flow<List<BuildInformation>> =
    localDataSource.getBuilds().map { list -> list.map { it.toDomain() } }

  override suspend fun createBuild(buildInformation: BuildInformation) {
    localDataSource.createBuild(
      buildEntity = BuildEntity(godId = buildInformation.god.id, name = buildInformation.name),
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

  override suspend fun syncWithPatchVersion(blockToSync: suspend () -> Unit) =
    withContext(Dispatchers.IO) {
      val newPatchVersion = networkDataSource.getPatchVersion().version
      val oldPatchVersion = patchVersionDataSource.getPatchVersion().firstOrNull()
      if (oldPatchVersion == null || oldPatchVersion != newPatchVersion) {
        syncPatchVersion()
        blockToSync()
      }
    }

  /**
   * Grabs the latest patch version from the remote data source and stores it in shared prefs data source
   */
  private suspend fun syncPatchVersion() {
    patchVersionDataSource.setPatchVersion(networkDataSource.getPatchVersion().version)
  }
}