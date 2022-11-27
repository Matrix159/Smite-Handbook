package com.matrix.shared.data.local.db

import com.matrix.shared.data.local.db.dao.BuildDao
import com.matrix.shared.data.local.db.dao.GodDao
import com.matrix.shared.data.local.db.dao.ItemDao

expect abstract class AppDatabase {
  abstract fun godDao(): GodDao
  abstract fun itemDao(): ItemDao
  abstract fun buildDao(): BuildDao
}

expect val smiteDatabase: AppDatabase