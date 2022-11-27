package com.matrix.shared.data.local.db.dao

import com.matrix.shared.data.local.db.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

expect interface ItemDao {
  fun getAll(): Flow<List<ItemEntity>>
  fun getItem(itemId: Int): Flow<ItemEntity>
  suspend fun insertAll(items: List<ItemEntity>)
}
