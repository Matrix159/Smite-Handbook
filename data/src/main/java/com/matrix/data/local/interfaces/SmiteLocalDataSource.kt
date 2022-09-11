package com.matrix.data.local.interfaces

import com.matrix.data.local.db.entity.GodEntity
import com.matrix.data.local.db.entity.ItemEntity

interface SmiteLocalDataSource {
  /**
   * Saves the god list and patch version to the local data source
   * @param gods List of god entities to save
   */
  suspend fun saveGods(gods: List<GodEntity>)

  /**
   * Retrieves the saved god list with an attach smite patch version
   */
  suspend fun readGods(): List<GodEntity>

  /**
   * Saves the item list and patch version to the local data source
   * @param items List of items to save
   */
  suspend fun saveItems(items: List<ItemEntity>)

  /**
   * Retrieves the saved item list with an attach smite patch version
   */
  suspend fun readItems(): List<ItemEntity>
}