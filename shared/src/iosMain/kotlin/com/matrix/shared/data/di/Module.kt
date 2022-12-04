package com.matrix.shared.data.di

import com.matrix.shared.data.local.PatchVersionDataSourceImpl
import com.matrix.shared.data.local.interfaces.PatchVersionDataSource
import com.matrix.shared.sqldelight.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
  single { DatabaseDriverFactory() }
  single<PatchVersionDataSource> { PatchVersionDataSourceImpl() }
}