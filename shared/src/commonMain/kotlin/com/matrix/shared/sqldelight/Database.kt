package com.matrix.shared.sqldelight
import com.matrix.SmiteHandbookDatabase

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
  private val database = SmiteHandbookDatabase(databaseDriverFactory.createDriver())
  private val dbQuery = database.smiteHandbookDatabaseQueries

  internal fun clearDatabase() {
    dbQuery.transaction {
      database.buildEntityQueries.selectAllBuilds().executeAsList()
    }
  }
}