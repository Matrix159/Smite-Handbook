package com.matrix.materializedsmite.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.matrix.materializedsmite.database.entities.SMITE_GODS_TABLE_NAME
import com.matrix.materializedsmite.database.entities.SmiteGods
import kotlinx.coroutines.flow.Flow

@Dao
interface SmiteDao {
  @Query("SELECT * FROM $SMITE_GODS_TABLE_NAME")
  fun getSmiteGods(): Flow<List<SmiteGods>>
}