package com.matrix.materializedsmite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matrix.materializedsmite.database.dao.SmiteDao
import com.matrix.materializedsmite.database.entities.SmiteGods

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [SmiteGods::class], version = 1, exportSchema = false)
abstract class SmiteDatabase : RoomDatabase() {

  abstract fun smiteDao(): SmiteDao
}