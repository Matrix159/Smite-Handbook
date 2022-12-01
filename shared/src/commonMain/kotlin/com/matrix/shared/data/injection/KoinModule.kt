package com.matrix.shared.data.injection

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.local.SmiteDatabaseLocalDataSource
import com.matrix.shared.data.local.interfaces.SmiteLocalDataSource
import com.matrix.shared.data.network.SmiteApiRemoteDataSource
import com.matrix.shared.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
import org.koin.dsl.module

fun appKoinModule() = module {
  factory<SmiteRemoteDataSource> { SmiteApiRemoteDataSource() }
  factory<SmiteLocalDataSource>{ SmiteDatabaseLocalDataSource(get()) }
  factory<SmiteRepository> { OfflineFirstSmiteRepository(get(), get(), get()) }
}