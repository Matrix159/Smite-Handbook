package com.matrix.materializedsmite.hilt

import com.matrix.api.SmiteApi
import com.matrix.materializedsmite.repositories.smite.SmiteRepository
import com.matrix.materializedsmite.repositories.smite.impl.SmiteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

  companion object {
    @Provides
    fun provideSmiteApi(): SmiteApi {
      return SmiteApi()
    }
  }

  @Binds
  abstract fun bindSmiteRepository(smiteRepositoryImpl: SmiteRepositoryImpl): SmiteRepository
}
