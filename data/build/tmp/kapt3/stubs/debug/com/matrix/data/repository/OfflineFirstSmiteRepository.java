package com.matrix.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00140\u0010H\u0016J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00102\u0006\u0010\u0017\u001a\u00020\u0012H\u0016J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00140\u00102\u0006\u0010\u0017\u001a\u00020\u0012H\u0016J\u0014\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00140\u0010H\u0016J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00102\u0006\u0010\u001d\u001a\u00020\u0012H\u0016J\u0014\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00140\u0010H\u0016J\u0011\u0010\u001f\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0011\u0010!\u001a\u00020\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0011\u0010\"\u001a\u00020\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0011\u0010#\u001a\u00020\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006$"}, d2 = {"Lcom/matrix/data/repository/OfflineFirstSmiteRepository;", "Lcom/matrix/domain/contracts/SmiteRepository;", "networkDataSource", "Lcom/matrix/data/network/interfaces/SmiteRemoteDataSource;", "localDataSource", "Lcom/matrix/data/local/interfaces/SmiteLocalDataSource;", "patchVersionDataSource", "Lcom/matrix/data/local/interfaces/PatchVersionDataSource;", "(Lcom/matrix/data/network/interfaces/SmiteRemoteDataSource;Lcom/matrix/data/local/interfaces/SmiteLocalDataSource;Lcom/matrix/data/local/interfaces/PatchVersionDataSource;)V", "createBuild", "", "buildInformation", "Lcom/matrix/domain/models/BuildInformation;", "(Lcom/matrix/domain/models/BuildInformation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBuild", "getBuild", "Lkotlinx/coroutines/flow/Flow;", "buildId", "", "getBuilds", "", "getGod", "Lcom/matrix/domain/models/GodInformation;", "godId", "getGodSkins", "Lcom/matrix/domain/models/GodSkinInformation;", "getGods", "getItem", "Lcom/matrix/domain/models/ItemInformation;", "itemId", "getItems", "sync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncGods", "syncItems", "syncPatchVersion", "data_debug"})
public final class OfflineFirstSmiteRepository implements com.matrix.domain.contracts.SmiteRepository {
    private final com.matrix.data.network.interfaces.SmiteRemoteDataSource networkDataSource = null;
    private final com.matrix.data.local.interfaces.SmiteLocalDataSource localDataSource = null;
    private final com.matrix.data.local.interfaces.PatchVersionDataSource patchVersionDataSource = null;
    
    @javax.inject.Inject
    public OfflineFirstSmiteRepository(@org.jetbrains.annotations.NotNull
    com.matrix.data.network.interfaces.SmiteRemoteDataSource networkDataSource, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.interfaces.SmiteLocalDataSource localDataSource, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.interfaces.PatchVersionDataSource patchVersionDataSource) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.domain.models.GodInformation>> getGods() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.matrix.domain.models.GodInformation> getGod(int godId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.domain.models.GodSkinInformation>> getGodSkins(int godId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.domain.models.ItemInformation>> getItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.matrix.domain.models.ItemInformation> getItem(int itemId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.matrix.domain.models.BuildInformation>> getBuilds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.matrix.domain.models.BuildInformation> getBuild(int buildId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object createBuild(@org.jetbrains.annotations.NotNull
    com.matrix.domain.models.BuildInformation buildInformation, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object deleteBuild(@org.jetbrains.annotations.NotNull
    com.matrix.domain.models.BuildInformation buildInformation, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object sync(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * Grabs the latest patch version from the remote data source and stores it in shared prefs data source
     */
    private final java.lang.Object syncPatchVersion(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object syncGods(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object syncItems(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}