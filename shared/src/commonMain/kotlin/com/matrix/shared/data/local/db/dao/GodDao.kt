package com.matrix.shared.data.local.db.dao

import com.matrix.shared.data.local.db.entity.GodEntity
import kotlinx.coroutines.flow.Flow

expect interface GodDao {
  fun getAll(): Flow<List<GodEntity>>

  fun getGod(godId: Int): Flow<GodEntity>

  suspend fun insertAll(gods: List<GodEntity>)
}
