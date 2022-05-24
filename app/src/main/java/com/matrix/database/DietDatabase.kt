package com.matrix.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matrix.database.dao.DietDao
import com.matrix.database.entities.DietEntry

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [DietEntry::class], version = 1, exportSchema = false)
abstract class DietDatabase : RoomDatabase() {

  abstract fun dietDao(): DietDao
}