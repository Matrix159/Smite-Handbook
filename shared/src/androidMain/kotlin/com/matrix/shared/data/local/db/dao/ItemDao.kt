package com.matrix.shared.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.shared.data.local.db.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
actual interface ItemDao {
  @Query("SELECT * FROM items")
  actual fun getAll(): Flow<List<ItemEntity>>

  @Query("SELECT * from items where id = :itemId")
  actual fun getItem(itemId: Int): Flow<ItemEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  actual suspend fun insertAll(items: List<ItemEntity>)
}
