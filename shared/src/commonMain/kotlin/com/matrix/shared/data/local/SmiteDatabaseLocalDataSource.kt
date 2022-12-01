package com.matrix.shared.data.local

import com.matrix.SmiteHandbookDatabase
import com.matrix.shared.data.local.db.entity.BuildDbResult
import com.matrix.shared.data.local.db.entity.BuildEntity
import com.matrix.shared.data.local.db.entity.GodEntity
import com.matrix.shared.data.local.db.entity.ItemEntity
import com.matrix.shared.data.local.interfaces.SmiteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SmiteDatabaseLocalDataSource constructor(
  private val database: SmiteHandbookDatabase,
) : SmiteLocalDataSource {

  override suspend fun saveGods(gods: List<GodEntity>) {} //database.godDao().insertAll(gods)

  override fun getGods(): Flow<List<GodEntity>> = flow {
    emit(
      emptyList()
    )
  } // database.godDao().getAll()

  override fun getGod(godId: Int): Flow<GodEntity> = flow {
    //GodEntity()
    //database.godDao().getGod(godId)
  }

  override suspend fun saveItems(items: List<ItemEntity>) {} // database.itemDao().insertAll(items)

  override fun getItems(): Flow<List<ItemEntity>> = flow {
    emit(
      emptyList()
    )
  }//database.itemDao().getAll()
  override fun getItem(itemId: Int): Flow<ItemEntity> = flow { }//database.itemDao().getItem(itemId)
  override suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>) {}
    //database.buildDao().createBuild(buildEntity, itemIds)

  override fun getBuilds(): Flow<List<BuildDbResult>> = flow{}// database.buildDao().getAll()
  override fun getBuild(buildId: Int): Flow<BuildDbResult> = flow{} //database.buildDao().getBuild(buildId)

  override suspend fun deleteBuild(buildEntity: BuildEntity) {}
    //database.buildDao().deleteBuildEntity(buildEntity)
}