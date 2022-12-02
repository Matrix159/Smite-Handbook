package com.matrix.shared.data.injection

import com.matrix.ItemEntity
import com.matrix.SmiteHandbookDatabase
import com.matrix.shared.data.local.PatchVersionDataSourceImpl
import com.matrix.shared.data.local.interfaces.PatchVersionDataSource
import com.matrix.shared.data.model.items.ItemDescription
import com.matrix.shared.sqldelight.DatabaseDriverFactory
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val itemDescriptionAdapter = object : ColumnAdapter<ItemDescription, String> {
  override fun decode(databaseValue: String) = Json.decodeFromString<ItemDescription>(databaseValue)
  override fun encode(value: ItemDescription) = Json.encodeToString(value)
}

inline fun <reified T : Any> getAdapter() = object : ColumnAdapter<T, String> {
  override fun decode(databaseValue: String) = Json.decodeFromString<T>(databaseValue)
  override fun encode(value: T) = Json.encodeToString(value)
}

fun androidKoinModule() = module {
  factory<PatchVersionDataSource> { PatchVersionDataSourceImpl(get()) }
  factory {
    SmiteHandbookDatabase(
      DatabaseDriverFactory(get()).createDriver(),
      ItemEntityAdapter = ItemEntity.Adapter(
        itemDescriptionAdapter = getAdapter()
      )
    )
  }
}
