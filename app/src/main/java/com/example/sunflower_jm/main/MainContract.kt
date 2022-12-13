package com.example.sunflower_jm.main

import androidx.annotation.WorkerThread
import com.example.sunflower_jm.db.SunFlowerEntity

interface MainContract {

    interface View {

        @WorkerThread
        fun updateItems(items: List<SunFlowerEntity>)
    }

    interface Presenter {

        fun obtainLoadItems()

        fun delete(item: SunFlowerEntity)
    }
}