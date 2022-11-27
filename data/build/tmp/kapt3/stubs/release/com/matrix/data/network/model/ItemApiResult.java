package com.matrix.data.network.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b=\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 ]2\u00020\u0001:\u0002\\]B\u00cb\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u0003\u0012\b\b\u0001\u0010\r\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0002\u0010\u0019B\u0089\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u001aJ\t\u0010@\u001a\u00020\u0005H\u00c6\u0003J\t\u0010A\u001a\u00020\u0005H\u00c6\u0003J\t\u0010B\u001a\u00020\u0003H\u00c6\u0003J\t\u0010C\u001a\u00020\u0005H\u00c6\u0003J\t\u0010D\u001a\u00020\u0013H\u00c6\u0003J\t\u0010E\u001a\u00020\u0005H\u00c6\u0003J\t\u0010F\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010H\u001a\u00020\u0003H\u00c6\u0003J\t\u0010I\u001a\u00020\u0005H\u00c6\u0003J\t\u0010J\u001a\u00020\u0005H\u00c6\u0003J\t\u0010K\u001a\u00020\u0003H\u00c6\u0003J\t\u0010L\u001a\u00020\u000bH\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\t\u0010O\u001a\u00020\u0003H\u00c6\u0003J\u00ab\u0001\u0010P\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010Q\u001a\u00020\u00132\b\u0010R\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010S\u001a\u00020\u0003H\u00d6\u0001J\t\u0010T\u001a\u00020\u0005H\u00d6\u0001J!\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020\u00002\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[H\u00c7\u0001R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010!R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u001c\u001a\u0004\b#\u0010\u001eR\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u001c\u001a\u0004\b%\u0010\u001eR\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u001c\u001a\u0004\b\'\u0010!R\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b(\u0010\u001c\u001a\u0004\b)\u0010*R\u001c\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u001c\u001a\u0004\b,\u0010!R\u001c\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u001c\u001a\u0004\b.\u0010\u001eR\u001c\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u001c\u001a\u0004\b0\u0010!R\u001c\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b1\u0010\u001c\u001a\u0004\b2\u0010!R\u001c\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u001c\u001a\u0004\b4\u0010\u001eR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b5\u0010\u001c\u001a\u0004\b6\u0010\u001eR\u001c\u0010\u0010\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b7\u0010\u001c\u001a\u0004\b8\u0010!R\u001c\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b9\u0010\u001c\u001a\u0004\b:\u0010\u001eR\u001c\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b;\u0010\u001c\u001a\u0004\b<\u0010=R\u001c\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b>\u0010\u001c\u001a\u0004\b?\u0010\u001e\u00a8\u0006^"}, d2 = {"Lcom/matrix/data/network/model/ItemApiResult;", "", "seen1", "", "activeFlag", "", "childItemID", "deviceName", "glyph", "iconID", "itemDescription", "Lcom/matrix/data/network/model/ItemDescription;", "itemID", "itemTier", "price", "restrictedRoles", "rootItemID", "shortDesc", "startingItem", "", "type", "itemIconURL", "retMsg", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;ILcom/matrix/data/network/model/ItemDescription;IIILjava/lang/String;ILjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILcom/matrix/data/network/model/ItemDescription;IIILjava/lang/String;ILjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActiveFlag$annotations", "()V", "getActiveFlag", "()Ljava/lang/String;", "getChildItemID$annotations", "getChildItemID", "()I", "getDeviceName$annotations", "getDeviceName", "getGlyph$annotations", "getGlyph", "getIconID$annotations", "getIconID", "getItemDescription$annotations", "getItemDescription", "()Lcom/matrix/data/network/model/ItemDescription;", "getItemID$annotations", "getItemID", "getItemIconURL$annotations", "getItemIconURL", "getItemTier$annotations", "getItemTier", "getPrice$annotations", "getPrice", "getRestrictedRoles$annotations", "getRestrictedRoles", "getRetMsg$annotations", "getRetMsg", "getRootItemID$annotations", "getRootItemID", "getShortDesc$annotations", "getShortDesc", "getStartingItem$annotations", "getStartingItem", "()Z", "getType$annotations", "getType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "data_release"})
@kotlinx.serialization.Serializable
public final class ItemApiResult {
    @org.jetbrains.annotations.NotNull
    public static final com.matrix.data.network.model.ItemApiResult.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String activeFlag = null;
    private final int childItemID = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String deviceName = null;
    
    /**
     * "y" or "n"
     */
    @org.jetbrains.annotations.NotNull
    private final java.lang.String glyph = null;
    private final int iconID = 0;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.ItemDescription itemDescription = null;
    private final int itemID = 0;
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
    @org.jetbrains.annotations.Nullable
    private final java.lang.String retMsg = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.ItemApiResult copy(@org.jetbrains.annotations.NotNull
    java.lang.String activeFlag, int childItemID, @org.jetbrains.annotations.NotNull
    java.lang.String deviceName, @org.jetbrains.annotations.NotNull
    java.lang.String glyph, int iconID, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.ItemDescription itemDescription, int itemID, int itemTier, int price, @org.jetbrains.annotations.NotNull
    java.lang.String restrictedRoles, int rootItemID, @org.jetbrains.annotations.NotNull
    java.lang.String shortDesc, boolean startingItem, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String itemIconURL, @org.jetbrains.annotations.Nullable
    java.lang.String retMsg) {
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
    
    @kotlin.jvm.JvmStatic
    public static final void write$Self(@org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.ItemApiResult self, @org.jetbrains.annotations.NotNull
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    public ItemApiResult(@org.jetbrains.annotations.NotNull
    java.lang.String activeFlag, int childItemID, @org.jetbrains.annotations.NotNull
    java.lang.String deviceName, @org.jetbrains.annotations.NotNull
    java.lang.String glyph, int iconID, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.ItemDescription itemDescription, int itemID, int itemTier, int price, @org.jetbrains.annotations.NotNull
    java.lang.String restrictedRoles, int rootItemID, @org.jetbrains.annotations.NotNull
    java.lang.String shortDesc, boolean startingItem, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String itemIconURL, @org.jetbrains.annotations.Nullable
    java.lang.String retMsg) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getActiveFlag() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "ActiveFlag")
    @java.lang.Deprecated
    public static void getActiveFlag$annotations() {
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getChildItemID() {
        return 0;
    }
    
    @kotlinx.serialization.SerialName(value = "ChildItemId")
    @java.lang.Deprecated
    public static void getChildItemID$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDeviceName() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "DeviceName")
    @java.lang.Deprecated
    public static void getDeviceName$annotations() {
    }
    
    /**
     * "y" or "n"
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    /**
     * "y" or "n"
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGlyph() {
        return null;
    }
    
    /**
     * "y" or "n"
     */
    @kotlinx.serialization.SerialName(value = "Glyph")
    @java.lang.Deprecated
    public static void getGlyph$annotations() {
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int getIconID() {
        return 0;
    }
    
    @kotlinx.serialization.SerialName(value = "IconId")
    @java.lang.Deprecated
    public static void getIconID$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.ItemDescription component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.ItemDescription getItemDescription() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "ItemDescription")
    @java.lang.Deprecated
    public static void getItemDescription$annotations() {
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int getItemID() {
        return 0;
    }
    
    @kotlinx.serialization.SerialName(value = "ItemId")
    @java.lang.Deprecated
    public static void getItemID$annotations() {
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int getItemTier() {
        return 0;
    }
    
    @kotlinx.serialization.SerialName(value = "ItemTier")
    @java.lang.Deprecated
    public static void getItemTier$annotations() {
    }
    
    public final int component9() {
        return 0;
    }
    
    public final int getPrice() {
        return 0;
    }
    
    @kotlinx.serialization.SerialName(value = "Price")
    @java.lang.Deprecated
    public static void getPrice$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRestrictedRoles() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "RestrictedRoles")
    @java.lang.Deprecated
    public static void getRestrictedRoles$annotations() {
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int getRootItemID() {
        return 0;
    }
    
    @kotlinx.serialization.SerialName(value = "RootItemId")
    @java.lang.Deprecated
    public static void getRootItemID$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getShortDesc() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "ShortDesc")
    @java.lang.Deprecated
    public static void getShortDesc$annotations() {
    }
    
    public final boolean component13() {
        return false;
    }
    
    public final boolean getStartingItem() {
        return false;
    }
    
    @kotlinx.serialization.SerialName(value = "StartingItem")
    @java.lang.Deprecated
    public static void getStartingItem$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getType() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Type")
    @java.lang.Deprecated
    public static void getType$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getItemIconURL() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "itemIcon_URL")
    @java.lang.Deprecated
    public static void getItemIconURL$annotations() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRetMsg() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "ret_msg")
    @java.lang.Deprecated
    public static void getRetMsg$annotations() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/matrix/data/network/model/ItemApiResult$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/matrix/data/network/model/ItemApiResult;", "data_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlinx.serialization.KSerializer<com.matrix.data.network.model.ItemApiResult> serializer() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/matrix/data/network/model/ItemApiResult.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/matrix/data/network/model/ItemApiResult;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "data_release"})
    @java.lang.Deprecated
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.matrix.data.network.model.ItemApiResult> {
        @org.jetbrains.annotations.NotNull
        public static final com.matrix.data.network.model.ItemApiResult.$serializer INSTANCE = null;
        
        private $serializer() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public com.matrix.data.network.model.ItemApiResult deserialize(@org.jetbrains.annotations.NotNull
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public kotlinx.serialization.descriptors.SerialDescriptor getDescriptor() {
            return null;
        }
        
        @java.lang.Override
        public void serialize(@org.jetbrains.annotations.NotNull
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull
        com.matrix.data.network.model.ItemApiResult value) {
        }
        
        @org.jetbrains.annotations.NotNull
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
}