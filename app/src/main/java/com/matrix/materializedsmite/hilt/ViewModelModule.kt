package com.matrix.materializedsmite.hilt

import com.matrix.materializedsmite.data.smite.SmiteRepository
import com.matrix.materializedsmite.data.smite.impl.SmiteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

  @Provides
  fun provideSmiteRepo(): SmiteRepository {
    return SmiteRepositoryImpl()
  }
}
