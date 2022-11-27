package com.matrix.data.local;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000bH\u0016J\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/matrix/data/local/PatchVersionDataSourceImpl;", "Lcom/matrix/data/local/interfaces/PatchVersionDataSource;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "PATCH_VERSION_KEY", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getContext", "()Landroid/content/Context;", "getPatchVersion", "Lkotlinx/coroutines/flow/Flow;", "setPatchVersion", "", "patchVersion", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class PatchVersionDataSourceImpl implements com.matrix.data.local.interfaces.PatchVersionDataSource {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PATCH_VERSION_KEY = null;
    
    @javax.inject.Inject
    public PatchVersionDataSourceImpl(@org.jetbrains.annotations.NotNull
    @dagger.hilt.android.qualifiers.ApplicationContext
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object setPatchVersion(@org.jetbrains.annotations.NotNull
    java.lang.String patchVersion, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.lang.String> getPatchVersion() {
        return null;
    }
}