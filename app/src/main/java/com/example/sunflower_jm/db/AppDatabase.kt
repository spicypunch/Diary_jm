package com.example.sunflower_jm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
Room 데이터베이스 클래스는 3가지 조건이 만족되어야 함
1. @Database 어노테이션에서 데이터베이스와 관련된 모든 Entity를 나열함
2. RoomDatabase를 상속하는 추상 클래스여야 함
3. DAO를 반환하고 인수가 존재하지 않는 추상 함수가 있음
 */
@Database(entities = arrayOf(DiaryEntity::class), version = 1) //조건 1에 해당
abstract class AppDatabase : RoomDatabase() { //조건 2에 해당
    abstract fun getDiaryDao() : DiaryDao //조건 3에 해당
    /*
    companion object를 사용하여 만약 appDatabase가 null이면 객체를 생성하고 null이 아니면 기존 객체를 반환하는 싱클톤 패턴 함수를 구현
    companion object이란 static 대신 동반 객체를 사용하여 정적 멤버를 정의하는 것
    싱글톤 패턴이란 한 객체를 여러 클래스에서 글로벌하게 쓰는 게 더 효율적인 데 이를 싱클톤 패턴이라고함
    */
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
