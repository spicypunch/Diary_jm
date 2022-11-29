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
interface SunFlowerDao {
    @Query("SELECT * FROM SunFlowerEntity")
    fun getAll(): List<SunFlowerEntity>

    @Insert
    fun insertItem(item: SunFlowerEntity)

    @Delete
    fun deleteItem(item: SunFlowerEntity)

    @Update
    fun updateItem(item: SunFlowerEntity)

    @Query("SELECT * FROM SunFlowerEntity WHERE id = :id")
    fun selectItem(id: Int)
}