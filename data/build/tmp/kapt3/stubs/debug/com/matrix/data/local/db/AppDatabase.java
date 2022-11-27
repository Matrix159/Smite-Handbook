package com.matrix.data.local.db;

import java.lang.System;

@androidx.room.Database(entities = {com.matrix.data.local.db.entity.GodEntity.class, com.matrix.data.local.db.entity.ItemEntity.class, com.matrix.data.local.db.entity.BuildEntity.class, com.matrix.data.local.db.entity.BuildItemCrossRef.class}, version = 1, autoMigrations = {})
@androidx.room.TypeConverters(value = {com.matrix.data.local.db.Converters.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/matrix/data/local/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "buildDao", "Lcom/matrix/data/local/db/dao/BuildDao;", "godDao", "Lcom/matrix/data/local/db/dao/GodDao;", "itemDao", "Lcom/matrix/data/local/db/dao/ItemDao;", "data_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.matrix.data.local.db.dao.GodDao godDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.matrix.data.local.db.dao.ItemDao itemDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.matrix.data.local.db.dao.BuildDao buildDao();
}