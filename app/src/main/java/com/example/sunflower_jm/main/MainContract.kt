package com.example.sunflower_jm.main

import androidx.annotation.WorkerThread
import com.example.sunflower_jm.db.DiaryEntity

interface MainContract {

    interface View {
        @WorkerThread
        fun updateItems(items: MutableList<DiaryEntity>)
    }

    interface Presenter {

        fun obtainLoadItems()

        fun delete(item: DiaryEntity)
    }
}