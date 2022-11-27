package com.matrix.data.local;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\'\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\n0\u0010H\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010\u0016\u001a\u00020\u000bH\u0016J\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\n0\u0010H\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00102\u0006\u0010\u001a\u001a\u00020\u000bH\u0016J\u0014\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\n0\u0010H\u0016J\u001f\u0010\u001c\u001a\u00020\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00150\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\u00062\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00190\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/matrix/data/local/SmiteDatabaseLocalDataSource;", "Lcom/matrix/data/local/interfaces/SmiteLocalDataSource;", "database", "Lcom/matrix/data/local/db/AppDatabase;", "(Lcom/matrix/data/local/db/AppDatabase;)V", "createBuild", "", "buildEntity", "Lcom/matrix/data/local/db/entity/BuildEntity;", "itemIds", "", "", "(Lcom/matrix/data/local/db/entity/BuildEntity;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBuild", "(Lcom/matrix/data/local/db/entity/BuildEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBuild", "Lkotlinx/coroutines/flow/Flow;", "Lcom/matrix/data/local/db/entity/BuildDbResult;", "buildId", "getBuilds", "getGod", "Lcom/matrix/data/local/db/entity/GodEntity;", "godId", "getGods", "getItem", "Lcom/matrix/data/local/db/entity/ItemEntity;", "itemId", "getItems", "saveGods", "gods", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveItems", "items", "data_release"})
@javax.inject.Singleton
public final class SmiteDatabaseLocalDataSource implements com.matrix.data.local.interfaces.SmiteLocalDataSource {
    private final com.matrix.data.local.db.AppDatabase database = null;
    
    @javax.inject.Inject
    public SmiteDatabaseLocalDataSource(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.AppDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object saveGods(@org.jetbrains.annotations.NotNull
    java.util.List<com.matrix.data.local.db.entity.GodEntity> gods, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.data.local.db.entity.GodEntity>> getGods() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.matrix.data.local.db.entity.GodEntity> getGod(int godId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object saveItems(@org.jetbrains.annotations.NotNull
    java.util.List<com.matrix.data.local.db.entity.ItemEntity> items, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.data.local.db.entity.ItemEntity>> getItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.matrix.data.local.db.entity.ItemEntity> getItem(int itemId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object createBuild(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildEntity buildEntity, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> itemIds, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.data.local.db.entity.BuildDbResult>> getBuilds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.matrix.data.local.db.entity.BuildDbResult> getBuild(int buildId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object deleteBuild(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildEntity buildEntity, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}