package com.matrix.shared.data.local.db.dao

import com.matrix.shared.data.local.db.entity.GodEntity
import kotlinx.coroutines.flow.Flow

actual interface GodDao {
  actual fun getAll(): Flow<List<GodEntity>>

  actual fun getGod(godId: Int): Flow<GodEntity>

  actual suspend fun insertAll(gods: List<GodEntity>)
}
