package com.matrix.shared.sqldelight

import com.matrix.GodEntity
import com.matrix.ItemEntity
import com.matrix.SmiteHandbookDatabase
import com.matrix.shared.data.local.getAdapter
import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
  fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DatabaseDriverFactory): SmiteHandbookDatabase {
  val driver = driverFactory.createDriver()
  return SmiteHandbookDatabase(
    driver,
    ItemEntityAdapter = ItemEntity.Adapter(
      itemDescriptionAdapter = getAdapter()
    ),
    GodEntityAdapter = GodEntity.Adapter(
      abilityDetails1Adapter = getAdapter(),
      abilityDetails2Adapter = getAdapter(),
      abilityDetails3Adapter = getAdapter(),
      abilityDetails4Adapter = getAdapter(),
      abilityDetails5Adapter = getAdapter(),
      basicAttackAdapter = getAdapter(),
    )
  )
  // Do more work with the database (see below).
}