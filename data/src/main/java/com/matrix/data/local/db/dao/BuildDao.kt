package com.matrix.data.local.db.dao

import androidx.room.*
import com.matrix.data.local.db.entity.BuildDbResult
import com.matrix.data.local.db.entity.BuildEntity
import com.matrix.data.local.db.entity.BuildItemCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildDao {
  @Transaction
  @Query("SELECT * FROM builds")
  fun getAll(): Flow<List<BuildDbResult>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertBuildEntity(buildEntity: BuildEntity): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertBuildItemCrossRef(buildItemCrossRef: BuildItemCrossRef)

  @Transaction
  suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>) {
    val newBuildId = insertBuildEntity(buildEntity)
    for (itemId in itemIds) {
      insertBuildItemCrossRef(
        BuildItemCrossRef(
          buildId = newBuildId,
          itemId = itemId
        )
      )
    }
  }

  @Delete
  suspend fun deleteBuildEntity(buildEntity: BuildEntity)
}
