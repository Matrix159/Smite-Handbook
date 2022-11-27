package com.matrix.data.service;

import java.lang.System;

@androidx.hilt.work.HiltWorker
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0011\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/matrix/data/service/PatchSyncWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "smiteRepository", "Lcom/matrix/domain/contracts/SmiteRepository;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lcom/matrix/domain/contracts/SmiteRepository;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "data_release"})
public final class PatchSyncWorker extends androidx.work.CoroutineWorker {
    private final android.content.Context appContext = null;
    private final androidx.work.WorkerParameters workerParams = null;
    private final com.matrix.domain.contracts.SmiteRepository smiteRepository = null;
    @org.jetbrains.annotations.NotNull
    public static final com.matrix.data.service.PatchSyncWorker.Companion Companion = null;
    
    @dagger.assisted.AssistedInject
    public PatchSyncWorker(@org.jetbrains.annotations.NotNull
    @dagger.assisted.Assisted
    android.content.Context appContext, @org.jetbrains.annotations.NotNull
    @dagger.assisted.Assisted
    androidx.work.WorkerParameters workerParams, @org.jetbrains.annotations.NotNull
    com.matrix.domain.contracts.SmiteRepository smiteRepository) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/matrix/data/service/PatchSyncWorker$Companion;", "", "()V", "startUpSyncWork", "Landroidx/work/OneTimeWorkRequest;", "data_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Expedited one time work to sync data on app startup
         */
        @org.jetbrains.annotations.NotNull
        public final androidx.work.OneTimeWorkRequest startUpSyncWork() {
            return null;
        }
    }
}