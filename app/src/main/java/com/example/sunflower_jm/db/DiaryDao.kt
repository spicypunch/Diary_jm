package com.example.sunflower_jm.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/*
데이터 접근 객체

어노테이션을 통해 어떤 구성 요소인지 꼭 알려줘야함
 */
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

//    @Query("SELECT * FROM SunFlowerEntity WHERE id = :id")
//    fun selectItem(id: Int)
}