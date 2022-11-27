package com.matrix.shared.data.local.db.dao

import com.matrix.shared.data.local.db.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

actual interface ItemDao {
  actual fun getAll(): Flow<List<ItemEntity>>
  actual fun getItem(itemId: Int): Flow<ItemEntity>
  actual suspend fun insertAll(items: List<ItemEntity>)
}
