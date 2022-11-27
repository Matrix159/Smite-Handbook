package com.matrix.shared.data.repository

import co.touchlab.kermit.Logger
import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.local.PatchVersionDataSourceImpl
import com.matrix.shared.data.local.db.entity.BuildDbResult
import com.matrix.shared.data.local.db.entity.BuildEntity
import com.matrix.shared.data.local.db.entity.GodEntity
import com.matrix.shared.data.local.db.entity.ItemEntity
import com.matrix.shared.data.local.interfaces.PatchVersionDataSource
import com.matrix.shared.data.local.interfaces.SmiteLocalDataSource
import com.matrix.shared.data.models.BuildInformation
import com.matrix.shared.data.models.GodInformation
import com.matrix.shared.data.models.GodSkinInformation
import com.matrix.shared.data.models.ItemInformation
import com.matrix.shared.data.network.SmiteApiRemoteDataSource
import com.matrix.shared.data.network.interfaces.SmiteRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OfflineFirstSmiteRepository constructor(
  private val networkDataSource: SmiteRemoteDataSource = SmiteApiRemoteDataSource(),
  private val localDataSource: SmiteLocalDataSource = object : SmiteLocalDataSource {
    override suspend fun saveGods(gods: List<GodEntity>) {
      TODO("Not yet implemented")
    }

    override fun getGods(): Flow<List<GodEntity>> {
      return flow {
        emit(emptyList())
      }
    }

    override fun getGod(godId: Int): Flow<GodEntity> {
      TODO("Not yet implemented")
    }

    override suspend fun saveItems(items: List<ItemEntity>) {
      TODO("Not yet implemented")
    }

    override fun getItems(): Flow<List<ItemEntity>> {
      TODO("Not yet implemented")
    }

    override fun getItem(itemId: Int): Flow<ItemEntity> {
      TODO("Not yet implemented")
    }

    override suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>) {
      TODO("Not yet implemented")
    }

    override fun getBuilds(): Flow<List<BuildDbResult>> {
      TODO("Not yet implemented")
    }

    override fun getBuild(buildId: Int): Flow<BuildDbResult> {
      TODO("Not yet implemented")
    }

    override suspend fun deleteBuild(buildEntity: BuildEntity) {
      TODO("Not yet implemented")
    }

  },// SmiteDatabaseLocalDataSource(),
  private val patchVersionDataSource: PatchVersionDataSource = PatchVersionDataSourceImpl(),
) : SmiteRepository {

  val logger by lazy { Logger.withTag(OfflineFirstSmiteRepository::class.simpleName!!) }

  override fun getGods(): Flow<List<GodInformation>> =
    localDataSource.getGods().map { entityList -> entityList.map { it.toDomain() } }

  override fun getGod(godId: Int): Flow<GodInformation> =
    localDataSource.getGod(godId).map { it.toDomain() }

  override fun getGodSkins(godId: Int): Flow<List<GodSkinInformation>> =
    flow { emit(networkDataSource.getGodSkins(godId).map { it.toDomain() }) }

  override fun getItems(): Flow<List<ItemInformation>> =
    localDataSource.getItems().map { entityList -> entityList.map { it.toDomain() } }

  override fun getItem(itemId: Int): Flow<ItemInformation> =
    localDataSource.getItem(itemId).map { it.toDomain() }




  override fun getBuilds(): Flow<List<BuildInformation>> =
    localDataSource.getBuilds().map { list -> list.map { it.toDomain() } }

  override fun getBuild(buildId: Int): Flow<BuildInformation> =
    localDataSource.getBuild(buildId).map { it.toDomain() }

  override suspend fun createBuild(buildInformation: BuildInformation) {
    localDataSource.createBuild(
      buildEntity = BuildEntity(
        id = buildInformation.id,
        godId = buildInformation.god.id,
        name = buildInformation.name
      ),
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
    val currentPatchVersion: String? = patchVersionDataSource.getPatchVersion().firstOrNull()
    val newData = networkDataSource.getGods()
    localDataSource.saveGods(newData.map {
      GodEntity.fromApi(it, patchVersion = currentPatchVersion)
    })
    logger.d("Saved new god data to local storage")
  }

  private suspend fun syncItems() = withContext(Dispatchers.Default) {
    val currentPatchVersion: String? = patchVersionDataSource.getPatchVersion().firstOrNull()
    val newData = networkDataSource.getItems()
    localDataSource.saveItems(newData.map {
      ItemEntity.fromApi(it, patchVersion = currentPatchVersion)
    })
    logger.d("Saved new item data to local storage")
  }
}