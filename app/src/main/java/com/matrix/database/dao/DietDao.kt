package com.matrix.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.database.entities.DIET_ENTRIES_TABLE_NAME
import com.matrix.database.entities.DietEntry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface DietDao {
  @Query("SELECT * FROM ${DIET_ENTRIES_TABLE_NAME}")
  fun getDietEntries(): Flow<List<DietEntry>>

  @Query("SELECT * FROM $DIET_ENTRIES_TABLE_NAME")
  fun getDietEntriesByDateTime(): Flow<List<DietEntry>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(dietEntry: DietEntry)

  @Query("DELETE FROM $DIET_ENTRIES_TABLE_NAME WHERE id = :id")
  suspend fun delete(id: Int)
}