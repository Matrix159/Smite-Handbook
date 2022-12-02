//package com.matrix.shared.data.local.db.dao
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Transaction
//import com.matrix.shared.data.local.db.entity.BuildDbResult
//import com.matrix.shared.data.local.db.entity.BuildEntity
//import com.matrix.shared.data.local.db.entity.BuildItemCrossRef
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//actual interface BuildDao {
//  @Transaction
//  @Query("SELECT * FROM builds")
//  actual fun getAll(): Flow<List<BuildDbResult>>
//
//  @Transaction
//  @Query("SELECT * FROM builds where id = :buildId")
//  actual fun getBuild(buildId: Int): Flow<BuildDbResult>
//
//  @Insert(onConflict = OnConflictStrategy.REPLACE)
//  actual suspend fun insertBuildEntity(buildEntity: BuildEntity): Long
//
//  @Insert(onConflict = OnConflictStrategy.REPLACE)
//  actual suspend fun insertBuildItemCrossRef(buildItemCrossRef: BuildItemCrossRef)
//
//  @Transaction
//  actual suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>) {
//    val newBuildId = insertBuildEntity(buildEntity)
//    for (itemId in itemIds) {
//      insertBuildItemCrossRef(
//        BuildItemCrossRef(
//          buildId = newBuildId.toInt(),
//          itemId = itemId
//        )
//      )
//    }
//  }
//
//  @Delete
//  actual suspend fun deleteBuildEntity(buildEntity: BuildEntity)
//}
