//package com.matrix.shared.data.local.db.dao
//
//import com.matrix.shared.data.local.db.entity.BuildDbResult
//import com.matrix.shared.data.local.db.entity.BuildEntity
//import com.matrix.shared.data.local.db.entity.BuildItemCrossRef
//import kotlinx.coroutines.flow.Flow
//
////expect interface BuildDao {
////
////  fun getAll(): Flow<List<BuildDbResult>>
////
////  fun getBuild(buildId: Int): Flow<BuildDbResult>
////
////  suspend fun insertBuildEntity(buildEntity: BuildEntity): Long
////
////  suspend fun insertBuildItemCrossRef(buildItemCrossRef: BuildItemCrossRef)
////
////  open suspend fun createBuild(buildEntity: BuildEntity, itemIds: List<Int>)
////
////  suspend fun deleteBuildEntity(buildEntity: BuildEntity)
////}
