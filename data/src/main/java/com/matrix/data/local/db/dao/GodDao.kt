package com.matrix.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.data.local.db.entity.GodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GodDao {
  @Query("SELECT * FROM gods")
  fun getAll(): Flow<List<GodEntity>>

  @Query("SELECT * FROM gods WHERE id = :godId")
  fun getGod(godId: Int): Flow<GodEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(gods: List<GodEntity>)
}
