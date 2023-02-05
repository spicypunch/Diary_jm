package com.example.sunflower_jm.view.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.model.DiaryEntity

class AddViewModel(private val diaryDao: DiaryDao) : ViewModel() {

    private var _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    fun insertItem(itemImage: String?, itemTitle: String, itemContent: String) {

        if (itemTitle.isBlank() || itemContent.isBlank()) {
            _success.value = false
            return
        }
        Thread {
            diaryDao.insertItem(DiaryEntity(null, itemImage, itemTitle, itemContent))
        }.start()
        _success.value = true
    }
}
