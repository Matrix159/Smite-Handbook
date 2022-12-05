package com.matrix.shared.sqldelight

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.matrix.SmiteHandbookDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

internal actual class DatabaseDriverFactory(private val context: Context) {
  actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(
      SmiteHandbookDatabase.Schema,
      context,
      "smiteHandbook.db",
      callback = object : AndroidSqliteDriver.Callback(SmiteHandbookDatabase.Schema) {
        override fun onConfigure(db: SupportSQLiteDatabase) {
          db.setForeignKeyConstraintsEnabled(true)
        }
      })
  }
}