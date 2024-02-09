package com.matrix.shared.testing.fakes

import com.matrix.shared.data.local.interfaces.SmiteLocalDataSource
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeSmiteLocalDataSource : SmiteLocalDataSource {
  private val localGods = mutableListOf<GodInformation>()
  private val localItems = mutableListOf<ItemInformation>()
  private val localBuilds = mutableListOf<BuildInformation>()
  //private val localBuildItemCrossRef = mutableListOf<BuildItemCrossRef>()

  override suspend fun saveGods(gods: List<GodInformation>) {
    localGods.addAll(gods)
    val distinctEntities = localGods.distinctBy { it.id }
    localGods.clear()
    localGods.addAll(distinctEntities)
  }

  // Make sure to return copies of the lists to avoid using the references to them
  override fun getGods(): Flow<List<GodInformation>> = flowOf(localGods.map { it.copy() })
  override fun getGod(godId: Long): Flow<GodInformation> = flowOf(localGods.first { it.id == godId })

  override suspend fun saveItems(items: List<ItemInformation>) {
    localItems.addAll(items)
    val distinctEntities = localItems.distinctBy { it.itemID }
    localItems.clear()
    localItems.addAll(distinctEntities)
  }

  // Make sure to return copies of the lists to avoid using the references to them
  override fun getItems(isActive: Boolean): Flow<List<ItemInformation>> =
    flowOf(localItems.filter { it.activeFlag == isActive }.map { it.copy() })

  override fun getItem(itemId: Long): Flow<ItemInformation> = flowOf(localItems.first { it.itemID == itemId })

  override suspend fun saveBuild(buildInformation: BuildInformation) {
    // Mimic inserting with a REPLACE policy on conflict
    val foundBuild = localBuilds.find { it.id != null && it.id == buildInformation.id }
    foundBuild?.let {
      localBuilds.remove(foundBuild)
    }
    localBuilds.add(buildInformation)
  }

  override suspend fun updateGodInBuild(buildId: Long, godId: Long) {
    val build = localBuilds.first { it.id == buildId }
    localBuilds.remove(build)
    localBuilds.add(build.copy(god = localGods.first { it.id == godId }))
  }

  override suspend fun updateItemsInBuild(buildId: Long, itemIds: List<Long>) {
    val build = localBuilds.first { it.id == buildId }
    localBuilds.remove(build)
    localBuilds.add(build.copy(items = itemIds.map { localItems.first { item -> item.itemID == it } }))
  }

  override fun getBuilds(): Flow<List<BuildInformation>> = flowOf(localBuilds.map { it.copy() })

  override fun getBuild(buildId: Long): Flow<BuildInformation> = flowOf(localBuilds.first { it.id == buildId })

  override suspend fun deleteBuild(buildId: Long) {
    localBuilds.removeAll { it.id == buildId }
  }
}