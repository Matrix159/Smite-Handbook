package com.matrix.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

const val DIET_ENTRIES_TABLE_NAME = "diet_entries"

@Entity(tableName = DIET_ENTRIES_TABLE_NAME)
data class DietEntry(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val foodImageUrl: String? = null,
  val foodName: String,
  val healthStatus: String,
)
