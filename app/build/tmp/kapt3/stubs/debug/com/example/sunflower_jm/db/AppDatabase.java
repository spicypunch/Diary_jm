package com.example.sunflower_jm.db;

import java.lang.System;

@androidx.room.Database(entities = {com.example.sunflower_jm.db.SunFlowerEntity.class}, version = 1)
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/example/sunflower_jm/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "getSunFlowerDao", "Lcom/example/sunflower_jm/db/SunFlowerDao;", "Companion", "app_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.sunflower_jm.db.AppDatabase.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String databaseName = "db_sunflower";
    @org.jetbrains.annotations.Nullable()
    private static com.example.sunflower_jm.db.AppDatabase appDatabase;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.sunflower_jm.db.SunFlowerDao getSunFlowerDao();
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u000fR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0010"}, d2 = {"Lcom/example/sunflower_jm/db/AppDatabase$Companion;", "", "()V", "appDatabase", "Lcom/example/sunflower_jm/db/AppDatabase;", "getAppDatabase", "()Lcom/example/sunflower_jm/db/AppDatabase;", "setAppDatabase", "(Lcom/example/sunflower_jm/db/AppDatabase;)V", "databaseName", "", "getDatabaseName", "()Ljava/lang/String;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDatabaseName() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.example.sunflower_jm.db.AppDatabase getAppDatabase() {
            return null;
        }
        
        public final void setAppDatabase(@org.jetbrains.annotations.Nullable()
        com.example.sunflower_jm.db.AppDatabase p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.example.sunflower_jm.db.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}