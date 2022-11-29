package com.matrix.shared.sqldelight

import android.content.Context
import com.matrix.SmiteHandbookDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
  actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(SmiteHandbookDatabase.Schema, context, "smiteHandbook.db")
  }
}