package com.matrix.data.local

import com.matrix.data.local.db.AppDatabase
import com.matrix.data.local.db.entity.GodEntity
import com.matrix.data.local.db.entity.ItemEntity
import com.matrix.data.local.interfaces.SmiteLocalDataSource
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
}