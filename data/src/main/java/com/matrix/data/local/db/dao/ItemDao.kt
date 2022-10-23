package com.matrix.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.data.local.db.entity.ItemEntity

@Dao
interface ItemDao {
  @Query("SELECT * FROM items")
  fun getAll(): List<ItemEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(items: List<ItemEntity>)
}
