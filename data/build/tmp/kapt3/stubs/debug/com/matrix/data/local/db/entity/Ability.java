package com.matrix.data.local.db.entity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0007H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f\u00a8\u0006\u001c"}, d2 = {"Lcom/matrix/data/local/db/entity/Ability;", "", "id", "", "description", "Lcom/matrix/data/local/db/entity/AbilityDescription;", "summary", "", "url", "(JLcom/matrix/data/local/db/entity/AbilityDescription;Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Lcom/matrix/data/local/db/entity/AbilityDescription;", "getId", "()J", "getSummary", "()Ljava/lang/String;", "getUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "data_debug"})
public final class Ability {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded
    private final com.matrix.data.local.db.entity.AbilityDescription description = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String summary = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String url = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability copy(long id, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.AbilityDescription description, @org.jetbrains.annotations.NotNull
    java.lang.String summary, @org.jetbrains.annotations.NotNull
    java.lang.String url) {
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
    
    public Ability(long id, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.AbilityDescription description, @org.jetbrains.annotations.NotNull
    java.lang.String summary, @org.jetbrains.annotations.NotNull
    java.lang.String url) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.AbilityDescription component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.AbilityDescription getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSummary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUrl() {
        return null;
    }
}