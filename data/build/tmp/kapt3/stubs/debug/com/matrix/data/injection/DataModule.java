package com.matrix.data.injection;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\'J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\'J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\'\u00a8\u0006\u0014"}, d2 = {"Lcom/matrix/data/injection/DataModule;", "", "()V", "bindsDataStoreSource", "Lcom/matrix/data/local/interfaces/PatchVersionDataSource;", "dataStoreSourceImpl", "Lcom/matrix/data/local/PatchVersionDataSourceImpl;", "bindsSmiteLocalDataSource", "Lcom/matrix/data/local/interfaces/SmiteLocalDataSource;", "smiteDatabaseLocalDataSource", "Lcom/matrix/data/local/SmiteDatabaseLocalDataSource;", "bindsSmiteRemoteDataSource", "Lcom/matrix/data/network/interfaces/SmiteRemoteDataSource;", "smiteApiDataSourceImpl", "Lcom/matrix/data/network/SmiteApiRemoteDataSource;", "bindsSmiteRepository", "Lcom/matrix/domain/contracts/SmiteRepository;", "offlineFirstSmiteRepository", "Lcom/matrix/data/repository/OfflineFirstSmiteRepository;", "Companion", "data_debug"})
@dagger.Module
public abstract class DataModule {
    @org.jetbrains.annotations.NotNull
    public static final com.matrix.data.injection.DataModule.Companion Companion = null;
    
    public DataModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.matrix.data.local.interfaces.SmiteLocalDataSource bindsSmiteLocalDataSource(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.SmiteDatabaseLocalDataSource smiteDatabaseLocalDataSource);
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.matrix.data.local.interfaces.PatchVersionDataSource bindsDataStoreSource(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.PatchVersionDataSourceImpl dataStoreSourceImpl);
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.matrix.data.network.interfaces.SmiteRemoteDataSource bindsSmiteRemoteDataSource(@org.jetbrains.annotations.NotNull
    com.matrix.data.network.SmiteApiRemoteDataSource smiteApiDataSourceImpl);
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.matrix.domain.contracts.SmiteRepository bindsSmiteRepository(@org.jetbrains.annotations.NotNull
    com.matrix.data.repository.OfflineFirstSmiteRepository offlineFirstSmiteRepository);
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\t"}, d2 = {"Lcom/matrix/data/injection/DataModule$Companion;", "", "()V", "provideDatabase", "Lcom/matrix/data/local/db/AppDatabase;", "context", "Landroid/content/Context;", "provideWorkManager", "Landroidx/work/WorkManager;", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @javax.inject.Singleton
        @dagger.Provides
        public final androidx.work.WorkManager provideWorkManager(@org.jetbrains.annotations.NotNull
        @dagger.hilt.android.qualifiers.ApplicationContext
        android.content.Context context) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @javax.inject.Singleton
        @dagger.Provides
        public final com.matrix.data.local.db.AppDatabase provideDatabase(@org.jetbrains.annotations.NotNull
        @dagger.hilt.android.qualifiers.ApplicationContext
        android.content.Context context) {
            return null;
        }
    }
}