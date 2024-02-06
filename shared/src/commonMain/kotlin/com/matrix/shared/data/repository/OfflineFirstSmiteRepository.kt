package com.matrix.shared.data.repository

import co.touchlab.kermit.Logger
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.local.interfaces.PatchVersionDataSource
import com.matrix.shared.data.local.interfaces.SmiteLocalDataSource
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import com.matrix.shared.data.model.skins.GodSkinInformation
import com.matrix.shared.data.model.toDomain
import com.matrix.shared.data.network.interfaces.SmiteRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class OfflineFirstSmiteRepository constructor(
  private val networkDataSource: SmiteRemoteDataSource,
  private val localDataSource: SmiteLocalDataSource,
  private val patchVersionDataSource: PatchVersionDataSource,
) : SmiteRepository {

  val logger by lazy { Logger.withTag(OfflineFirstSmiteRepository::class.simpleName!!) }

  override fun getGods(): Flow<List<GodInformation>> = localDataSource.getGods()

  override fun getGod(godId: Long): Flow<GodInformation> = localDataSource.getGod(godId)

  override fun getGodSkins(godId: Long): Flow<List<GodSkinInformation>> =
    flow { emit(networkDataSource.getGodSkins(godId).map { it.toDomain() }) }

  override fun getItems(): Flow<List<ItemInformation>> = localDataSource.getItems()

  override fun getItem(itemId: Long): Flow<ItemInformation> = localDataSource.getItem(itemId)

  override fun getBuilds(): Flow<List<BuildInformation>> = localDataSource.getBuilds()

  override fun getBuild(buildId: Long): Flow<BuildInformation> = localDataSource.getBuild(buildId)

  override suspend fun saveBuild(buildInformation: BuildInformation) = localDataSource.saveBuild(buildInformation)
  override suspend fun updateGodInBuild(buildId: Long, godId: Long) = localDataSource.updateGodInBuild(buildId, godId)

  override suspend fun updateItemsInBuild(buildId: Long, itemIds: List<Long>) = localDataSource.updateItemsInBuild(buildId, itemIds)

  override suspend fun deleteBuild(buildInformation: BuildInformation) {
    buildInformation.id?.let {
      localDataSource.deleteBuild(it)
    }
  }

  override suspend fun sync() = withContext(Dispatchers.Default) {
    val newPatchVersion = networkDataSource.getPatchVersion().version
    val oldPatchVersion = patchVersionDataSource.getPatchVersion().firstOrNull()
    if (
      getGods().first().isEmpty() ||
      getItems().first().isEmpty() ||
      oldPatchVersion == null ||
      oldPatchVersion != newPatchVersion
    ) {
      launch { syncGods() }
      launch { syncItems() }
      launch { syncPatchVersion() }
    }
  }

  /**
   * Grabs the latest patch version from the remote data source and stores it in shared prefs data source
   */
  private suspend fun syncPatchVersion() {
    patchVersionDataSource.setPatchVersion(networkDataSource.getPatchVersion().version)
  }

  private suspend fun syncGods() = withContext(Dispatchers.Default) {
    //val currentPatchVersion: String? = patchVersionDataSource.getPatchVersion().firstOrNull()
    val newData = networkDataSource.getGods()
    localDataSource.saveGods(newData.map {
      it.toDomain()
    })
    logger.d("Saved new god data to local storage")
  }

  private suspend fun syncItems() = withContext(Dispatchers.Default) {
    //val currentPatchVersion: String? = patchVersionDataSource.getPatchVersion().firstOrNull()
    val newData = networkDataSource.getItems()
    localDataSource.saveItems(newData.map {
      it.toDomain()
    })
    logger.d("Saved new item data to local storage")
  }
}