package com.example.sunflower_jm.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SunFlowerDao {
    @Query("SELECT * FROM SunFlowerEntity")
    fun getAll(): List<SunFlowerEntity>

    @Insert
    fun insertItem(item: SunFlowerEntity)

    @Delete
    fun deleteItem(item: SunFlowerEntity)

    @Update
    fun updateItem(item: SunFlowerEntity)

//    @Query("DELETE FROM SunFlowerEntity WHERE id = ")
}