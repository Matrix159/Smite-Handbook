package com.matrix.data.network;

import java.lang.System;

@kotlin.OptIn(markerClass = {kotlinx.serialization.ExperimentalSerializationApi.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0011\u0010\u0012\u001a\u00020\u0013H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/matrix/data/network/SmiteApiRemoteDataSource;", "Lcom/matrix/data/network/interfaces/SmiteRemoteDataSource;", "()V", "baseUrl", "", "client", "Lio/ktor/client/HttpClient;", "getGodSkins", "", "Lcom/matrix/data/network/model/GodSkinApiResult;", "godId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGods", "Lcom/matrix/data/network/model/GodApiResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItems", "Lcom/matrix/data/network/model/ItemApiResult;", "getPatchVersion", "Lcom/matrix/data/model/PatchVersionInfo;", "data_debug"})
public final class SmiteApiRemoteDataSource implements com.matrix.data.network.interfaces.SmiteRemoteDataSource {
    private final java.lang.String baseUrl = "https://smite-handbook.onrender.com";
    private final io.ktor.client.HttpClient client = null;
    
    @javax.inject.Inject
    public SmiteApiRemoteDataSource() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getGods(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.matrix.data.network.model.GodApiResult>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getGodSkins(int godId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.matrix.data.network.model.GodSkinApiResult>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getItems(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.matrix.data.network.model.ItemApiResult>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getPatchVersion(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.matrix.data.model.PatchVersionInfo> continuation) {
        return null;
    }
}