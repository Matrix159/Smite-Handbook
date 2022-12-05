package com.matrix.shared.data.local.interfaces

import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.Flow

internal interface SmiteLocalDataSource {
  /**
   * Saves the god list and patch version to the local data source
   * @param gods List of god entities to save
   */
  suspend fun saveGods(gods: List<GodInformation>)

  /**
   * Retrieves the saved god list with an attach smite patch version
   */
  fun getGods(): Flow<List<GodInformation>>

  fun getGod(godId: Long): Flow<GodInformation>

  /**
   * Saves the item list and patch version to the local data source
   * @param items List of items to save
   */
  suspend fun saveItems(items: List<ItemInformation>)

  /**
   * Retrieves the saved item list with an attach smite patch version
   */
  fun getItems(isActive: Boolean = true): Flow<List<ItemInformation>>
  fun getItem(itemId: Long): Flow<ItemInformation>

  suspend fun saveBuild(buildInformation: BuildInformation)
  fun getBuilds(): Flow<List<BuildInformation>>

  fun getBuild(buildId: Long): Flow<BuildInformation>

  suspend fun deleteBuild(buildId: Long)
}