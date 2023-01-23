package com.example.sunflower_jm.pattern

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sunflower_jm.db.DiaryEntity
import com.example.sunflower_jm.db.DiaryDao

class MainViewModel(private val diaryDao: DiaryDao): ViewModel() {

    class Factory(private val diaryDao: DiaryDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(diaryDao) as T
        }
    }

    private val _items = MutableLiveData<MutableList<DiaryEntity>>()
    val items: LiveData<MutableList<DiaryEntity>>
        get() = _items

    init {
        _items.value = mutableListOf()
    }

    fun obtainLoadItems() {

        Thread {
            _items.postValue(diaryDao.getAll() as MutableList<DiaryEntity>)
        }.start()
    }

    fun delete(item: DiaryEntity) {
        Thread {
            diaryDao.deleteItem(item)
            obtainLoadItems()
        }.start()
    }
}