package com.matrix.shared.sqldelight

import com.matrix.SmiteHandbookDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver


actual class DatabaseDriverFactory {
  actual fun createDriver(): SqlDriver {
    val driver = NativeSqliteDriver(SmiteHandbookDatabase.Schema, "smiteHandbook.db")
    driver.execute(null, "PRAGMA foreign_keys=ON", 0)
    return driver
  }
}