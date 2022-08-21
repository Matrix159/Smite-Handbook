package com.matrix.data.injection

import com.matrix.data.local.SmiteLocalDatasourceImpl
import com.matrix.data.local.interfaces.SmiteLocalDataSource
import com.matrix.data.network.SmiteRemoteDataSourceImpl
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.data.repository.SmiteRepositoryImpl
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
  abstract fun bindsSmiteLocalDataSource(
    smiteLocalDataSourceImpl: SmiteLocalDatasourceImpl
  ): SmiteLocalDataSource

  @Binds
  abstract fun bindsSmiteRemoteDataSource(
    smiteApiDataSourceImpl: SmiteRemoteDataSourceImpl
  ): SmiteRemoteDataSource

  @Binds
  @Singleton
  abstract fun bindsSmiteRepository(
    smiteRepositoryImpl: SmiteRepositoryImpl
  ): SmiteRepository
}