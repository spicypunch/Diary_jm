package com.example.sunflower_jm.main

import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.DiaryEntity

class MainPresenter(
    private val diaryDao: DiaryDao,
    private val view: MainContract.View,
) : MainContract.Presenter {

    override fun obtainLoadItems() {
        Thread {
            val items = diaryDao.getAll()
            view.updateItems(items)
        }.start()
    }

    override fun delete(item: DiaryEntity) {
        Thread {
            diaryDao.deleteItem(item)
            obtainLoadItems()
        }.start()
    }
}