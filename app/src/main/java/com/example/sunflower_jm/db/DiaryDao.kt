package com.example.sunflower_jm.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sunflower_jm.db.model.DiaryEntity

@Dao
interface DiaryDao {
    @Query("SELECT * FROM DiaryEntity")
    fun getAll(): List<DiaryEntity>

    @Insert
    fun insertItem(item: DiaryEntity)

    @Delete
    fun deleteItem(item: DiaryEntity)

    @Update
    fun updateItem(item: DiaryEntity)

}