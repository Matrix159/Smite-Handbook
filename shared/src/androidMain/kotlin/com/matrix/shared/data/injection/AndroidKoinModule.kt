package com.matrix.shared.data.injection

import com.matrix.SmiteHandbookDatabase
import com.matrix.shared.data.local.PatchVersionDataSourceImpl
import com.matrix.shared.data.local.interfaces.PatchVersionDataSource
import com.matrix.shared.sqldelight.DatabaseDriverFactory
import org.koin.dsl.module

fun androidKoinModule() = module {
  factory<PatchVersionDataSource> { PatchVersionDataSourceImpl(get()) }
  factory { SmiteHandbookDatabase(DatabaseDriverFactory(get()).createDriver()) }
}
