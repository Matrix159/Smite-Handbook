package com.matrix.shared.data.local.db.dao

import com.matrix.shared.data.local.db.entity.BuildDbResult
import com.matrix.shared.data.local.db.entity.BuildEntity
import com.matrix.shared.data.local.db.entity.BuildItemCrossRef
import kotlinx.coroutines.flow.Flow

actual interface BuildDao {
  actual fun getAll(): Flow<List<BuildDbResult>>

  actual fun getBuild(buildId: Int): Flow<BuildDbResult>

  actual suspend fun insertBuildEntity(buildEntity: BuildEntity): Long

  actual suspend fun insertBuildItemCrossRef(buildItemCrossRef: BuildItemCrossRef)

  actual suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>) {
    val newBuildId = insertBuildEntity(buildEntity)
    for (itemId in itemIds) {
      insertBuildItemCrossRef(
        BuildItemCrossRef(
          buildId = newBuildId.toInt(),
          itemId = itemId
        )
      )
    }
  }

  actual suspend fun deleteBuildEntity(buildEntity: BuildEntity)
}
