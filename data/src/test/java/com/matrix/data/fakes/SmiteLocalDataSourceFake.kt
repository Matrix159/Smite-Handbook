package com.matrix.data.fakes

import com.matrix.data.local.db.entity.*
import com.matrix.data.local.interfaces.SmiteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SmiteLocalDataSourceFake : SmiteLocalDataSource {
  private val localGodEntities = mutableListOf<GodEntity>()
  private val localItemEntities = mutableListOf<ItemEntity>()
  private val localBuildEntities = mutableListOf<BuildEntity>()
  private val localBuildItemCrossRef = mutableListOf<BuildItemCrossRef>()

  override suspend fun saveGods(gods: List<GodEntity>) {
    localGodEntities.addAll(gods)
    val distinctEntities = localGodEntities.distinctBy { it.id }
    localGodEntities.clear()
    localGodEntities.addAll(distinctEntities)
  }

  override suspend fun readGods(): List<GodEntity> = localGodEntities

  override suspend fun saveItems(items: List<ItemEntity>) {
    localItemEntities.addAll(items)
    val distinctEntities = localItemEntities.distinctBy { it.id }
    localItemEntities.clear()
    localItemEntities.addAll(distinctEntities)
  }

  override suspend fun readItems(): List<ItemEntity> = localItemEntities

  override suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>) {
    // Mimic inserting with a REPLACE policy on conflict
    val lastId: Long = localBuildEntities.maxBy { it.godId }.godId.toLong()
    val newBuildEntity = buildEntity.copy(id = lastId + 1)
    localBuildEntities.add(newBuildEntity)
    val distinctBuildEntities = localBuildEntities.distinctBy { it.id }
    localBuildEntities.clear()
    localBuildEntities.addAll(distinctBuildEntities)
    itemIds.forEach {
      localBuildItemCrossRef.add(BuildItemCrossRef(newBuildEntity.id!!, it))
    }
    val distinctBuildItemCrossRef = localBuildItemCrossRef.distinctBy { it.buildId to it.itemId }
    localBuildItemCrossRef.clear()
    localBuildItemCrossRef.addAll(distinctBuildItemCrossRef)
  }

  override fun getBuilds(): Flow<List<BuildDbResult>> = flow {
    val buildDbResults = localBuildEntities.map { build ->
      val god: GodEntity = localGodEntities.find { god -> god.id == build.godId }!!
      val itemIds: List<Int> = localBuildItemCrossRef
        .filter { ref -> ref.buildId == build.id }
        .map { it.itemId }

      return@map BuildDbResult(
        build = build,
        god = god,
        items = localItemEntities.filter { itemIds.contains(it.id) }
      )

    }
    emit(buildDbResults)
  }

  override suspend fun deleteBuild(buildEntity: BuildEntity) {
    localBuildEntities.removeIf { it.id == buildEntity.id }
    localBuildItemCrossRef.removeIf { it.buildId == buildEntity.id }
  }
}