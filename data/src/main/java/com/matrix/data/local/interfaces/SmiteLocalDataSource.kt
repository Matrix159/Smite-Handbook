package com.matrix.data.local.interfaces

import com.matrix.data.local.LocalGodList
import com.matrix.data.local.LocalItemList
import com.matrix.domain.models.GodInformation
import com.matrix.domain.models.Item

interface SmiteLocalDataSource {
  /**
   * Saves the god list and patch version to the local data source
   * @param godInformation List of gods to save
   * @param patchVersion Current version of smite
   */
  suspend fun saveGods(godInformation: List<GodInformation>, patchVersion: String)

  /**
   * Retrieves the saved god list with an attach smite patch version
   * @return GodListWithPatchVersion
   */
  suspend fun readGods(): LocalGodList?

  /**
   * Saves the item list and patch version to the local data source
   * @param items List of gods to save
   * @param patchVersion Current version of smite
   */
  suspend fun saveItems(items: List<Item>, patchVersion: String)

  /**
   * Retrieves the saved item list with an attach smite patch version
   * @return GodListWithPatchVersion
   */
  suspend fun readItems(): LocalItemList?
}