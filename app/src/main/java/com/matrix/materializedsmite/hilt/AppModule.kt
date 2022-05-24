package com.matrix.materializedsmite.hilt

import android.content.Context
import androidx.room.Room
import com.matrix.api.SmiteApi
import com.matrix.database.DietDatabase
import com.matrix.database.dao.DietDao
import com.matrix.materializedsmite.repositories.smite.SmiteRepository
import com.matrix.materializedsmite.repositories.smite.impl.SmiteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

  companion object {
    @Provides
    fun provideSmiteApi(): SmiteApi {
      return SmiteApi()
    }

    @Provides
    fun provideDietDao(database: DietDatabase): DietDao {
      return database.dietDao()
    }

    @Provides
    @Singleton
    fun provideDietDatabase(@ApplicationContext appContext: Context): DietDatabase {
      return Room.databaseBuilder(
        appContext,
        DietDatabase::class.java,
        "diet_database"
      ).build()
    }
  }

  @Binds
  abstract fun bindSmiteRepository(smiteRepositoryImpl: SmiteRepositoryImpl): SmiteRepository
}
