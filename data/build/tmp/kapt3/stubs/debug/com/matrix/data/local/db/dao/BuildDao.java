package com.matrix.data.local.db.dao;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0097@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\rH\'J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0010\u001a\u00020\bH\'J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/matrix/data/local/db/dao/BuildDao;", "", "createBuild", "", "buildEntity", "Lcom/matrix/data/local/db/entity/BuildEntity;", "itemIds", "", "", "(Lcom/matrix/data/local/db/entity/BuildEntity;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBuildEntity", "(Lcom/matrix/data/local/db/entity/BuildEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "Lkotlinx/coroutines/flow/Flow;", "Lcom/matrix/data/local/db/entity/BuildDbResult;", "getBuild", "buildId", "insertBuildEntity", "", "insertBuildItemCrossRef", "buildItemCrossRef", "Lcom/matrix/data/local/db/entity/BuildItemCrossRef;", "(Lcom/matrix/data/local/db/entity/BuildItemCrossRef;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface BuildDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM builds")
    @androidx.room.Transaction
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.data.local.db.entity.BuildDbResult>> getAll();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM builds where id = :buildId")
    @androidx.room.Transaction
    public abstract kotlinx.coroutines.flow.Flow<com.matrix.data.local.db.entity.BuildDbResult> getBuild(int buildId);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertBuildEntity(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildEntity buildEntity, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertBuildItemCrossRef(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildItemCrossRef buildItemCrossRef, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Transaction
    public abstract java.lang.Object createBuild(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildEntity buildEntity, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> itemIds, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object deleteBuildEntity(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildEntity buildEntity, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 3)
    public final class DefaultImpls {
        
        @org.jetbrains.annotations.Nullable
        @androidx.room.Transaction
        public static java.lang.Object createBuild(@org.jetbrains.annotations.NotNull
        com.matrix.data.local.db.dao.BuildDao $this, @org.jetbrains.annotations.NotNull
        com.matrix.data.local.db.entity.BuildEntity buildEntity, @org.jetbrains.annotations.NotNull
        java.util.List<java.lang.Integer> itemIds, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super kotlin.Unit> p3) {
            return null;
        }
    }
}