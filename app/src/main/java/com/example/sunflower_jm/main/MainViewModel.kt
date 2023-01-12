package com.example.sunflower_jm.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunflower_jm.db.DiaryEntity
import com.example.sunflower_jm.db.DiaryDao

class MainViewModel(private val diaryDao: DiaryDao): ViewModel() {

    private val _items = MutableLiveData<List<DiaryEntity>>()
    val items: LiveData<List<DiaryEntity>>
        get() = _items

    init {
        _items.value = listOf()
    }

    fun obtainLoadItems() {

        Thread {
            _items.value = diaryDao.getAll()
//            view.updateItems(items as MutableList<DiaryEntity>)
        }.start()
    }

    fun delete(item: DiaryEntity) {
        Thread {
            diaryDao.deleteItem(item)
            obtainLoadItems()
        }.start()
    }
}