package com.matrix.shared.data.local

import com.matrix.BuildEntity
import com.matrix.GodEntity
import com.matrix.ItemEntity
import com.matrix.SmiteHandbookDatabase
import com.matrix.shared.data.local.interfaces.SmiteLocalDataSource
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
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

  override fun getItems(): Flow<List<ItemEntity>> = database.itemEntityQueries.selectAllItems().asFlow().mapToList()
  override fun getItem(itemId: Int): Flow<ItemEntity> = flow { }//database.itemDao().getItem(itemId)
  override suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>) {}
    //database.buildDao().createBuild(buildEntity, itemIds)

  override fun getBuilds(): Flow<List<BuildEntity>> = flow{}//database.buildEntityQueries.selectAllBuilds().asFlow().mapToList()
  override fun getBuild(buildId: Int): Flow<BuildEntity> = flow{} //database.buildDao().getBuild(buildId)

  override suspend fun deleteBuild(buildEntity: BuildEntity) {}
    //database.buildDao().deleteBuildEntity(buildEntity)
}