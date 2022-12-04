package com.matrix.shared.data.fakes

import com.matrix.shared.data.local.interfaces.SmiteLocalDataSource
import com.matrix.shared.data.model.builds.BuildInformation
import com.matrix.shared.data.model.gods.GodInformation
import com.matrix.shared.data.model.items.ItemInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SmiteLocalDataSourceFake : SmiteLocalDataSource {
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
  override fun getGod(godId: Long): Flow<GodInformation> {
    TODO("Not yet implemented")
  }

  override suspend fun saveItems(items: List<ItemInformation>) {
    localItems.addAll(items)
    val distinctEntities = localItems.distinctBy { it.itemID }
    localItems.clear()
    localItems.addAll(distinctEntities)
  }

  // Make sure to return copies of the lists to avoid using the references to them
  override fun getItems(isActive: Boolean): Flow<List<ItemInformation>> =
    flowOf(localItems.filter { it.activeFlag == isActive }.map { it.copy() })

  override fun getItem(itemId: Long): Flow<ItemInformation> {
    TODO("Not yet implemented")
  }

  override suspend fun saveBuild(buildInformation: BuildInformation) {
    // Mimic inserting with a REPLACE policy on conflict
    val foundBuild = localBuilds.find { it.id != null && it.id == buildInformation.id }
    foundBuild?.let {
      localBuilds.remove(foundBuild)
    }
    localBuilds.add(buildInformation)
//    val lastId: Int = localBuilds.maxBy { it.godId }.godId
//    val newBuildEntity = buildEntity.copy(id = lastId + 1)
//    localBuilds.add(newBuildEntity)
//    val distinctBuildEntities = localBuilds.distinctBy { it.id }
//    localBuilds.clear()
//    localBuilds.addAll(distinctBuildEntities)
//    itemIds.forEach {
//      localBuildItemCrossRef.add(BuildItemCrossRef(newBuildEntity.id!!, it))
//    }
//    val distinctBuildItemCrossRef = localBuildItemCrossRef.distinctBy { it.buildId to it.itemId }
//    localBuildItemCrossRef.clear()
//    localBuildItemCrossRef.addAll(distinctBuildItemCrossRef)
  }

  override fun getBuilds(): Flow<List<BuildInformation>> = flowOf( localBuilds.map { it.copy() })
//    val buildDbResults = localBuilds.map { build ->
//      val god: GodEntity = localGods.find { god -> god.id == build.godId }!!
//      val itemIds: List<Int> = localBuildItemCrossRef
//        .filter { ref -> ref.buildId == build.id }
//        .map { it.itemId }
//
//      return@map BuildDbResult(
//        build = build,
//        god = god,
//        items = localItems.filter { itemIds.contains(it.id) }
//      )
//
//    }

  override fun getBuild(buildId: Long): Flow<BuildInformation> {
    TODO("Not yet implemented")
  }

  override suspend fun deleteBuild(buildId: Long) {
    localBuilds.removeAll { it.id == buildId }
    //localBuildItemCrossRef.removeIf { it.buildId == buildEntity.id }
  }
}