package com.example.sunflower_jm.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiaryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "image") var image: String? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String
) : java.io.Serializable