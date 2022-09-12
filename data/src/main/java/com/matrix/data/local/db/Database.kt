package com.matrix.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.matrix.data.local.db.dao.BuildDao
import com.matrix.data.local.db.dao.GodDao
import com.matrix.data.local.db.dao.ItemDao
import com.matrix.data.local.db.entity.BuildEntity
import com.matrix.data.local.db.entity.BuildItemCrossRef
import com.matrix.data.local.db.entity.GodEntity
import com.matrix.data.local.db.entity.ItemEntity
import com.matrix.data.local.db.model.DescriptionValue
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// TODO: migrations
@TypeConverters(Converters::class)
@Database(
  entities =
  [
    GodEntity::class,
    ItemEntity::class,
    BuildEntity::class,
    BuildItemCrossRef::class,
  ],
  version = 1
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun godDao(): GodDao
  abstract fun itemDao(): ItemDao
  abstract fun buildDao(): BuildDao
}

class Converters {
  @TypeConverter
  fun fromList(value: List<DescriptionValue>) = Json.encodeToString(value)

  @TypeConverter
  fun toList(value: String) = Json.decodeFromString<List<DescriptionValue>>(value)
}