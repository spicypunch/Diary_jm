package com.example.sunflower_jm.db

import android.content.Context
import androidx.room.*
import com.example.sunflower_jm.db.model.DiaryEntity

@Database(entities = [DiaryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDiaryDao() : DiaryDao

    companion object {
        private const val DATABASE = "db_sunflower"
        private var appDatabase : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            if(appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE).build()
            }
            return appDatabase
        }
    }

}
