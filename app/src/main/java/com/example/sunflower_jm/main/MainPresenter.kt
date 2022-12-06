package com.example.sunflower_jm.main

import com.example.sunflower_jm.db.SunFlowerDao
import com.example.sunflower_jm.db.SunFlowerEntity

class MainPresenter(
    private val sunflowerDao: SunFlowerDao,
    private val view: MainContract.View,
) : MainContract.Presenter {

    override fun obtainLoadItems() {
        Thread {
            val items = sunflowerDao.getAll()
            view.updateItems(items)
        }.start()
    }

    override fun delete(item: SunFlowerEntity) {
        Thread {
            sunflowerDao.deleteItem(item)
            obtainLoadItems()
        }.start()
    }
}