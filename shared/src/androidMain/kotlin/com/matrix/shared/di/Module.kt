package com.matrix.shared.di

import com.matrix.shared.data.local.PatchVersionDataSourceImpl
import com.matrix.shared.data.local.interfaces.PatchVersionDataSource
import com.matrix.shared.sqldelight.DatabaseDriverFactory
import org.koin.dsl.module

//val itemDescriptionAdapter = object : ColumnAdapter<ItemDescription, String> {
//  override fun decode(databaseValue: String) = Json.decodeFromString<ItemDescription>(databaseValue)
//  override fun encode(value: ItemDescription) = Json.encodeToString(value)
//}



actual fun platformModule() = module {
  single<PatchVersionDataSource> { PatchVersionDataSourceImpl(get()) }
  single { DatabaseDriverFactory(get()) }
}
