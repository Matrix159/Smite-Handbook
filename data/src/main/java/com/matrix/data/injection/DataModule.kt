package com.matrix.data.injection

import android.content.Context
import androidx.work.WorkManager
import com.matrix.data.local.SharedPrefsDataSourceImpl
import com.matrix.data.local.SmiteLocalDatasourceImpl
import com.matrix.data.local.interfaces.SharedPrefsDataSource
import com.matrix.data.local.interfaces.SmiteLocalDataSource
import com.matrix.data.network.SmiteRemoteDataSourceImpl
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.data.repository.SmiteRepositoryImpl
import com.matrix.domain.contracts.SmiteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
  @Binds
  @Singleton
  abstract fun bindsSmiteLocalDataSource(
    smiteLocalDataSourceImpl: SmiteLocalDatasourceImpl
  ): SmiteLocalDataSource

  @Binds
  @Singleton
  abstract fun bindsSharedPrefsDataSource(
    sharedPrefsDataSourceImpl: SharedPrefsDataSourceImpl
  ): SharedPrefsDataSource

  @Binds
  @Singleton
  abstract fun bindsSmiteRemoteDataSource(
    smiteApiDataSourceImpl: SmiteRemoteDataSourceImpl
  ): SmiteRemoteDataSource

  @Binds
  @Singleton
  abstract fun bindsSmiteRepository(
    smiteRepositoryImpl: SmiteRepositoryImpl
  ): SmiteRepository

  companion object {
    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
      return WorkManager.getInstance(context)
    }
  }
}