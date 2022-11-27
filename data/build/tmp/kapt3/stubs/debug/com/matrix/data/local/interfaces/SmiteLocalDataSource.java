package com.matrix.data.local.interfaces;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\bH&J\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\rH&J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\r2\u0006\u0010\u0013\u001a\u00020\bH&J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\rH&J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u0006\u0010\u0017\u001a\u00020\bH&J\u0014\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00070\rH&J\u001f\u0010\u0019\u001a\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ\u001f\u0010\u001c\u001a\u00020\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00160\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001e"}, d2 = {"Lcom/matrix/data/local/interfaces/SmiteLocalDataSource;", "", "createBuild", "", "buildEntity", "Lcom/matrix/data/local/db/entity/BuildEntity;", "itemIds", "", "", "(Lcom/matrix/data/local/db/entity/BuildEntity;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBuild", "(Lcom/matrix/data/local/db/entity/BuildEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBuild", "Lkotlinx/coroutines/flow/Flow;", "Lcom/matrix/data/local/db/entity/BuildDbResult;", "buildId", "getBuilds", "getGod", "Lcom/matrix/data/local/db/entity/GodEntity;", "godId", "getGods", "getItem", "Lcom/matrix/data/local/db/entity/ItemEntity;", "itemId", "getItems", "saveGods", "gods", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveItems", "items", "data_debug"})
public abstract interface SmiteLocalDataSource {
    
    /**
     * Saves the god list and patch version to the local data source
     * @param gods List of god entities to save
     */
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object saveGods(@org.jetbrains.annotations.NotNull
    java.util.List<com.matrix.data.local.db.entity.GodEntity> gods, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Retrieves the saved god list with an attach smite patch version
     */
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.data.local.db.entity.GodEntity>> getGods();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.matrix.data.local.db.entity.GodEntity> getGod(int godId);
    
    /**
     * Saves the item list and patch version to the local data source
     * @param items List of items to save
     */
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object saveItems(@org.jetbrains.annotations.NotNull
    java.util.List<com.matrix.data.local.db.entity.ItemEntity> items, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Retrieves the saved item list with an attach smite patch version
     */
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.data.local.db.entity.ItemEntity>> getItems();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.matrix.data.local.db.entity.ItemEntity> getItem(int itemId);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object createBuild(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildEntity buildEntity, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> itemIds, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.data.local.db.entity.BuildDbResult>> getBuilds();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.matrix.data.local.db.entity.BuildDbResult> getBuild(int buildId);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteBuild(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildEntity buildEntity, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}