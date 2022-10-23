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
class SmiteLocalDataSourceImpl @Inject constructor(
  private val database: AppDatabase
) : SmiteLocalDataSource {

  override suspend fun saveGods(gods: List<GodEntity>) = database.godDao().insertAll(gods)

  override suspend fun readGods(): List<GodEntity> = database.godDao().getAll()

  override suspend fun saveItems(items: List<ItemEntity>) = database.itemDao().insertAll(items)

  override suspend fun readItems(): List<ItemEntity> = database.itemDao().getAll()
  override suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>) =
    database.buildDao().createBuild(buildEntity, itemIds)

  override fun getBuilds(): Flow<List<BuildDbResult>> = database.buildDao().getAll()
  override suspend fun deleteBuild(buildEntity: BuildEntity) =
    database.buildDao().deleteBuildEntity(buildEntity)
}