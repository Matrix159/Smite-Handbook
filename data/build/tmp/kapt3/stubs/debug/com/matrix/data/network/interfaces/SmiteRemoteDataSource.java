package com.matrix.data.network.interfaces;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0011\u0010\r\u001a\u00020\u000eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/matrix/data/network/interfaces/SmiteRemoteDataSource;", "", "getGodSkins", "", "Lcom/matrix/data/network/model/GodSkinApiResult;", "godId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGods", "Lcom/matrix/data/network/model/GodApiResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItems", "Lcom/matrix/data/network/model/ItemApiResult;", "getPatchVersion", "Lcom/matrix/data/model/PatchVersionInfo;", "data_debug"})
public abstract interface SmiteRemoteDataSource {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getGods(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.matrix.data.network.model.GodApiResult>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getGodSkins(int godId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.matrix.data.network.model.GodSkinApiResult>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getItems(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.matrix.data.network.model.ItemApiResult>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPatchVersion(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.matrix.data.model.PatchVersionInfo> continuation);
}