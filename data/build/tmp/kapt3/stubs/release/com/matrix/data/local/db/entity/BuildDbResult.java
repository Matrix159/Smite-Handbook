package com.matrix.data.local.db.entity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001d"}, d2 = {"Lcom/matrix/data/local/db/entity/BuildDbResult;", "", "build", "Lcom/matrix/data/local/db/entity/BuildEntity;", "god", "Lcom/matrix/data/local/db/entity/GodEntity;", "items", "", "Lcom/matrix/data/local/db/entity/ItemEntity;", "(Lcom/matrix/data/local/db/entity/BuildEntity;Lcom/matrix/data/local/db/entity/GodEntity;Ljava/util/List;)V", "getBuild", "()Lcom/matrix/data/local/db/entity/BuildEntity;", "getGod", "()Lcom/matrix/data/local/db/entity/GodEntity;", "getItems", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toDomain", "Lcom/matrix/domain/models/BuildInformation;", "toString", "", "data_release"})
public final class BuildDbResult {
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded
    private final com.matrix.data.local.db.entity.BuildEntity build = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Relation(parentColumn = "godId", entityColumn = "id")
    private final com.matrix.data.local.db.entity.GodEntity god = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Relation(parentColumn = "id", entityColumn = "id", associateBy = @androidx.room.Junction(parentColumn = "buildId", value = com.matrix.data.local.db.entity.BuildItemCrossRef.class, entityColumn = "itemId"))
    private final java.util.List<com.matrix.data.local.db.entity.ItemEntity> items = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.BuildDbResult copy(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildEntity build, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.GodEntity god, @org.jetbrains.annotations.NotNull
    java.util.List<com.matrix.data.local.db.entity.ItemEntity> items) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public BuildDbResult(@org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.BuildEntity build, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.GodEntity god, @org.jetbrains.annotations.NotNull
    java.util.List<com.matrix.data.local.db.entity.ItemEntity> items) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.BuildEntity component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.BuildEntity getBuild() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.GodEntity component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.GodEntity getGod() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.matrix.data.local.db.entity.ItemEntity> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.matrix.data.local.db.entity.ItemEntity> getItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.domain.models.BuildInformation toDomain() {
        return null;
    }
}