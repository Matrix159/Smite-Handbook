//package com.matrix.shared.data.local.db
//
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverter
//import androidx.room.TypeConverters
//import com.matrix.shared.data.local.db.dao.BuildDao
//import com.matrix.shared.data.local.db.dao.GodDao
//import com.matrix.shared.data.local.db.dao.ItemDao
//import com.matrix.shared.data.local.db.entity.BuildEntity
//import com.matrix.shared.data.local.db.entity.BuildItemCrossRef
//import com.matrix.shared.data.local.db.entity.GodEntity
//import com.matrix.shared.data.local.db.entity.ItemEntity
//import com.matrix.shared.data.models.DescriptionValue
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.encodeToString
//import kotlinx.serialization.json.Json
//
//@TypeConverters(Converters::class)
//@Database(
//  entities =
//  [
//    GodEntity::class,
//    ItemEntity::class,
//    BuildEntity::class,
//    BuildItemCrossRef::class,
//  ],
//  version = 1,
//  autoMigrations = [
//    //AutoMigration (from = 1, to = 2)
//  ]
//)
//actual abstract class AppDatabase : RoomDatabase() {
//  actual abstract fun godDao(): GodDao
//  actual abstract fun itemDao(): ItemDao
//  actual abstract fun buildDao(): BuildDao
//}
//
//
//actual val smiteDatabase: AppDatabase = Room.databaseBuilder(
//  KmmAppContext.appContext.androidContext,
//  AppDatabase::class.java,
//  "smite-handbook-db"
//).build()
//
//class Converters {
//  @TypeConverter
//  fun fromList(value: List<DescriptionValue>) = Json.encodeToString(value)
//
//  @TypeConverter
//  fun toList(value: String) = Json.decodeFromString<List<DescriptionValue>>(value)
//}