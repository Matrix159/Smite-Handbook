package com.matrix.presentation.hilt

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

  companion object {
//    @Provides
//    fun provideDietDao(database: SmiteDatabase): SmiteDao {
//      return database.smiteDao()
//    }
//
//    @Provides
//    @Singleton
//    fun provideDietDatabase(@ApplicationContext appContext: Context): SmiteDatabase {
//      return Room.databaseBuilder(
//        appContext,
//        SmiteDatabase::class.java,
//        "diet_database"
//      ).build()
//    }
  }
}
