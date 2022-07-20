package com.matrix.api.injection

import com.matrix.api.SmiteRemoteDataSource
import com.matrix.api.impl.SmiteRemoteDataSourceImpl
import com.matrix.api.impl.SmiteRepositoryImpl
import com.matrix.domain.contracts.SmiteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

  @Binds
  abstract fun bindsSmiteDataSource(
    smiteApiDataSourceImpl: SmiteRemoteDataSourceImpl
  ): SmiteRemoteDataSource

  @Binds
  @Singleton
  abstract fun bindsSmiteRepository(
    smiteRepositoryImpl: SmiteRepositoryImpl
  ): SmiteRepository
}