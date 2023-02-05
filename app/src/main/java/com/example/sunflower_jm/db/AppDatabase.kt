package com.example.sunflower_jm.db

import android.content.Context
import androidx.room.*
import com.example.sunflower_jm.db.model.DiaryEntity

@Database(entities = arrayOf(DiaryEntity::class), version = 1) //조건 1에 해당
abstract class AppDatabase : RoomDatabase() { //조건 2에 해당
    abstract fun getDiaryDao() : DiaryDao //조건 3에 해당

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
