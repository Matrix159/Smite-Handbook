package com.matrix.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.data.local.db.entity.GodEntity

@Dao
interface GodDao {
  @Query("SELECT * FROM gods")
  fun getAll(): List<GodEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(gods: List<GodEntity>)
}
