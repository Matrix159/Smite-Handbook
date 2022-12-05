package com.matrix.shared.di

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.local.SmiteDatabaseLocalDataSource
import com.matrix.shared.data.local.interfaces.SmiteLocalDataSource
import com.matrix.shared.data.network.SmiteApiRemoteDataSource
import com.matrix.shared.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun commonModule() = module {
  single<SmiteLocalDataSource>{ SmiteDatabaseLocalDataSource(get()) }
  factory<SmiteRemoteDataSource> { SmiteApiRemoteDataSource() }
  factory<SmiteRepository> { OfflineFirstSmiteRepository(get(), get(), get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
  startKoin {
    appDeclaration()
    modules(
      commonModule(),
      platformModule()
    )
  }

// iOS
fun initKoin() {
  startKoin {
    modules(platformModule() + commonModule())
  }
}

class SmiteRepositoryHelper : KoinComponent {
  private val repository : SmiteRepository by inject()
  @Throws(Exception::class)
  suspend fun sync() = repository.sync()
}