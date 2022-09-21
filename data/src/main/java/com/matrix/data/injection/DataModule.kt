package com.matrix.data.injection

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.matrix.data.local.PatchVersionDataSourceImpl
import com.matrix.data.local.SmiteDatabaseLocalDataSource
import com.matrix.data.local.db.AppDatabase
import com.matrix.data.local.interfaces.PatchVersionDataSource
import com.matrix.data.local.interfaces.SmiteLocalDataSource
import com.matrix.data.network.SmiteApiRemoteDataSource
import com.matrix.data.network.interfaces.SmiteRemoteDataSource
import com.matrix.data.repository.OfflineFirstSmiteRepository
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
    smiteDatabaseLocalDataSource: SmiteDatabaseLocalDataSource
  ): SmiteLocalDataSource

  @Binds
  @Singleton
  abstract fun bindsDataStoreSource(
    dataStoreSourceImpl: PatchVersionDataSourceImpl
  ): PatchVersionDataSource

  @Binds
  @Singleton
  abstract fun bindsSmiteRemoteDataSource(
    smiteApiDataSourceImpl: SmiteApiRemoteDataSource
  ): SmiteRemoteDataSource

  @Binds
  @Singleton
  abstract fun bindsSmiteRepository(
    offlineFirstSmiteRepository: OfflineFirstSmiteRepository
  ): SmiteRepository

  companion object {
    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
      return WorkManager.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
      context,
      AppDatabase::class.java, "smite-handbook-db"
    ).build()
  }
}