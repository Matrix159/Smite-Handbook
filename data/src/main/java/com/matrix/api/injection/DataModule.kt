package com.matrix.api.injection

import com.matrix.api.SmiteRemoteDataSource
import com.matrix.api.impl.SmiteRepositoryImpl
import com.matrix.api.impl.SmiteRemoteDataSourceImpl
import com.matrix.domain.contracts.SmiteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
  @Binds
  abstract fun bindsSmiteDataSource(
    smiteApiDataSourceImpl: SmiteRemoteDataSourceImpl
  ): SmiteRemoteDataSource

  @Binds
  abstract fun bindsSmiteRepository(
    smiteRepositoryImpl: SmiteRepositoryImpl
  ): SmiteRepository
}