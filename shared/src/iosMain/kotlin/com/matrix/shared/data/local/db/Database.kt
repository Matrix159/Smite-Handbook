package com.matrix.shared.data.local.db


import com.matrix.shared.data.local.db.dao.BuildDao
import com.matrix.shared.data.local.db.dao.GodDao
import com.matrix.shared.data.local.db.dao.ItemDao

actual abstract class AppDatabase {
  actual abstract fun godDao(): GodDao
  actual abstract fun itemDao(): ItemDao
  actual abstract fun buildDao(): BuildDao
}