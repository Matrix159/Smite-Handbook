//package com.matrix.shared.data.local.db.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.matrix.shared.data.local.db.entity.GodEntity
//import kotlinx.coroutines.flow.Flow
//
////@Dao
////actual interface GodDao {
////  @Query("SELECT * FROM gods")
////  actual fun getAll(): Flow<List<GodEntity>>
////
////  @Query("SELECT * FROM gods WHERE id = :godId")
////  actual fun getGod(godId: Int): Flow<GodEntity>
////
////  @Insert(onConflict = OnConflictStrategy.REPLACE)
////  actual suspend fun insertAll(gods: List<GodEntity>)
////}
