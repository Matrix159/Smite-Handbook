package com.matrix.shared.data.injection

import com.matrix.shared.data.contracts.SmiteRepository
import com.matrix.shared.data.repository.OfflineFirstSmiteRepository
import org.koin.dsl.module

fun appModule() = module {
  single<SmiteRepository> { OfflineFirstSmiteRepository(get(), get(), get()) }
}