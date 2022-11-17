package com.example.sunflower_jm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SunFlowerEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSunFlowerDao() : SunFlowerDao

    companion object {
        val databaseName = "db_sunflower"
        var appDatabase : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            if(appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
            }
            return appDatabase
        }
    }
}
