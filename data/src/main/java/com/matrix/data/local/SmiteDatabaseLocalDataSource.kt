package com.matrix.data.local

import com.matrix.data.local.db.AppDatabase
import com.matrix.data.local.db.entity.BuildDbResult
import com.matrix.data.local.db.entity.BuildEntity
import com.matrix.data.local.db.entity.GodEntity
import com.matrix.data.local.db.entity.ItemEntity
import com.matrix.data.local.interfaces.SmiteLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmiteDatabaseLocalDataSource @Inject constructor(
  private val database: AppDatabase
) : SmiteLocalDataSource {

  override suspend fun saveGods(gods: List<GodEntity>) = database.godDao().insertAll(gods)

  override fun readGods(): Flow<List<GodEntity>> = database.godDao().getAll()
  override fun getGod(godId: Int): Flow<GodEntity> = database.godDao().getGod(godId)

  override suspend fun saveItems(items: List<ItemEntity>) = database.itemDao().insertAll(items)

  override fun readItems(): Flow<List<ItemEntity>> = database.itemDao().getAll()
  override suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>) =
    database.buildDao().createBuild(buildEntity, itemIds)

  override fun getBuilds(): Flow<List<BuildDbResult>> = database.buildDao().getAll()
  override suspend fun deleteBuild(buildEntity: BuildEntity) =
    database.buildDao().deleteBuildEntity(buildEntity)
}