package com.example.sunflower_jm.db;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\'J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\n"}, d2 = {"Lcom/example/sunflower_jm/db/SunFlowerDao;", "", "deleteItem", "", "item", "Lcom/example/sunflower_jm/db/SunFlowerEntity;", "getAll", "", "insertItem", "updateItem", "app_debug"})
public abstract interface SunFlowerDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM SunFlowerEntity")
    public abstract java.util.List<com.example.sunflower_jm.db.SunFlowerEntity> getAll();
    
    @androidx.room.Insert()
    public abstract void insertItem(@org.jetbrains.annotations.NotNull()
    com.example.sunflower_jm.db.SunFlowerEntity item);
    
    @androidx.room.Delete()
    public abstract void deleteItem(@org.jetbrains.annotations.NotNull()
    com.example.sunflower_jm.db.SunFlowerEntity item);
    
    @androidx.room.Update()
    public abstract void updateItem(@org.jetbrains.annotations.NotNull()
    com.example.sunflower_jm.db.SunFlowerEntity item);
}