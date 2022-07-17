package com.matrix.api.injection

import com.matrix.api.SmiteApiDataSource
import com.matrix.api.impl.SmiteRepositoryImpl
import com.matrix.api.impl.SmiteApiDataSourceImpl
import com.matrix.domain.contracts.SmiteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
  @Binds
  abstract fun bindsSmiteDataSource(
    smiteApiDataSourceImpl: SmiteApiDataSourceImpl
  ): SmiteApiDataSource

  @Binds
  abstract fun bindsSmiteRepository(
    smiteRepositoryImpl: SmiteRepositoryImpl
  ): SmiteRepository
}