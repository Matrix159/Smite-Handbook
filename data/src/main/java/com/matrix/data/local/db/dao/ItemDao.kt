package com.matrix.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.data.local.db.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
  @Query("SELECT * FROM items")
  fun getAll(): Flow<List<ItemEntity>>

  @Query("SELECT * from items where id = :itemId")
  fun getItem(itemId: Int): Flow<ItemEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(items: List<ItemEntity>)
}
