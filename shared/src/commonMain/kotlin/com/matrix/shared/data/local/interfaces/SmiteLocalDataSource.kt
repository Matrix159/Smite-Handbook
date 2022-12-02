package com.matrix.shared.data.local.interfaces

import com.matrix.BuildEntity
import com.matrix.ItemEntity
import kotlinx.coroutines.flow.Flow

// TODO UPDATE THE TYPES BELOW
interface SmiteLocalDataSource {
  /**
   * Saves the god list and patch version to the local data source
   * @param gods List of god entities to save
   */
  suspend fun saveGods(gods: List<ItemEntity>)

  /**
   * Retrieves the saved god list with an attach smite patch version
   */
  fun getGods(): Flow<List<ItemEntity>>

  fun getGod(godId: Int): Flow<ItemEntity>

  /**
   * Saves the item list and patch version to the local data source
   * @param items List of items to save
   */
  suspend fun saveItems(items: List<ItemEntity>)

  /**
   * Retrieves the saved item list with an attach smite patch version
   */
  fun getItems(): Flow<List<ItemEntity>>
  fun getItem(itemId: Int): Flow<ItemEntity>

  suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>)
  fun getBuilds(): Flow<List<BuildEntity>>

  fun getBuild(buildId: Int): Flow<BuildEntity>

  suspend fun deleteBuild(buildEntity: BuildEntity)
}