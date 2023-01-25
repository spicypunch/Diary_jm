package com.example.sunflower_jm.db

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiaryEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "image") val image: String? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String
) : java.io.Serializable