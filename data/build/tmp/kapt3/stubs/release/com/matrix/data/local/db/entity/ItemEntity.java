package com.matrix.data.local.db.entity;

import java.lang.System;

@androidx.room.Entity(tableName = "items")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 B2\u00020\u0001:\u0001BB\u0089\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0016J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0005H\u00c6\u0003J\t\u00100\u001a\u00020\u0007H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0007H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0005H\u00c6\u0003J\t\u00107\u001a\u00020\u0007H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\rH\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\u00ab\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010<\u001a\u00020\u00072\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020\u0003H\u00d6\u0001J\u0006\u0010?\u001a\u00020@J\t\u0010A\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0015\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001aR\u0011\u0010\u0012\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0011\u0010\u0013\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R\u0011\u0010\u0014\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001c\u00a8\u0006C"}, d2 = {"Lcom/matrix/data/local/db/entity/ItemEntity;", "", "id", "", "patchVersion", "", "activeFlag", "", "childItemID", "deviceName", "glyph", "iconID", "itemDescription", "Lcom/matrix/data/local/db/entity/ItemDescription;", "itemTier", "price", "restrictedRoles", "rootItemID", "shortDesc", "startingItem", "type", "itemIconURL", "(ILjava/lang/String;ZILjava/lang/String;ZILcom/matrix/data/local/db/entity/ItemDescription;IILjava/lang/String;ILjava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "getActiveFlag", "()Z", "getChildItemID", "()I", "getDeviceName", "()Ljava/lang/String;", "getGlyph", "getIconID", "getId", "getItemDescription", "()Lcom/matrix/data/local/db/entity/ItemDescription;", "getItemIconURL", "getItemTier", "getPatchVersion", "getPrice", "getRestrictedRoles", "getRootItemID", "getShortDesc", "getStartingItem", "getType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toDomain", "Lcom/matrix/domain/models/ItemInformation;", "toString", "Companion", "data_release"})
public final class ItemEntity {
    @androidx.room.PrimaryKey
    private final int id = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String patchVersion = null;
    private final boolean activeFlag = false;
    private final int childItemID = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String deviceName = null;
    private final boolean glyph = false;
    private final int iconID = 0;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded
    private final com.matrix.data.local.db.entity.ItemDescription itemDescription = null;
    private final int itemTier = 0;
    private final int price = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String restrictedRoles = null;
    private final int rootItemID = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String shortDesc = null;
    private final boolean startingItem = false;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String type = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String itemIconURL = null;
    @org.jetbrains.annotations.NotNull
    public static final com.matrix.data.local.db.entity.ItemEntity.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.ItemEntity copy(int id, @org.jetbrains.annotations.Nullable
    java.lang.String patchVersion, boolean activeFlag, int childItemID, @org.jetbrains.annotations.NotNull
    java.lang.String deviceName, boolean glyph, int iconID, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.ItemDescription itemDescription, int itemTier, int price, @org.jetbrains.annotations.NotNull
    java.lang.String restrictedRoles, int rootItemID, @org.jetbrains.annotations.NotNull
    java.lang.String shortDesc, boolean startingItem, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String itemIconURL) {
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
    
    public ItemEntity(int id, @org.jetbrains.annotations.Nullable
    java.lang.String patchVersion, boolean activeFlag, int childItemID, @org.jetbrains.annotations.NotNull
    java.lang.String deviceName, boolean glyph, int iconID, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.ItemDescription itemDescription, int itemTier, int price, @org.jetbrains.annotations.NotNull
    java.lang.String restrictedRoles, int rootItemID, @org.jetbrains.annotations.NotNull
    java.lang.String shortDesc, boolean startingItem, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String itemIconURL) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPatchVersion() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final boolean getActiveFlag() {
        return false;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int getChildItemID() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDeviceName() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean getGlyph() {
        return false;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int getIconID() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.ItemDescription component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.ItemDescription getItemDescription() {
        return null;
    }
    
    public final int component9() {
        return 0;
    }
    
    public final int getItemTier() {
        return 0;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int getPrice() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRestrictedRoles() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int getRootItemID() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getShortDesc() {
        return null;
    }
    
    public final boolean component14() {
        return false;
    }
    
    public final boolean getStartingItem() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getItemIconURL() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.domain.models.ItemInformation toDomain() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a8\u0006\t"}, d2 = {"Lcom/matrix/data/local/db/entity/ItemEntity$Companion;", "", "()V", "fromApi", "Lcom/matrix/data/local/db/entity/ItemEntity;", "itemApiResult", "Lcom/matrix/data/network/model/ItemApiResult;", "patchVersion", "", "data_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.matrix.data.local.db.entity.ItemEntity fromApi(@org.jetbrains.annotations.NotNull
        com.matrix.data.network.model.ItemApiResult itemApiResult, @org.jetbrains.annotations.Nullable
        java.lang.String patchVersion) {
            return null;
        }
    }
}