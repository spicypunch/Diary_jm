package com.example.sunflower_jm.view.update

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.model.DiaryEntity

class UpdateViewModel(private val diaryDao: DiaryDao) : ViewModel() {

    private var id: Int? = null

    private var uriInfo: Uri? = null

    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var _map = MutableLiveData<HashMap<String, String>>()
    val map: LiveData<HashMap<String, String>>
        get() = _map

    private var _item = MutableLiveData<DiaryEntity>()
    val item: LiveData<DiaryEntity>
        get() = _item

    fun updateItem(item: DiaryEntity) {
        this.id = item.id
        _item.value = item
    }

    fun updateUri(uriInfo: Uri) {
        this.uriInfo = uriInfo
    }

    fun updateContent(itemTitle: String, itemContent: String) {

        if (itemTitle.isBlank() || itemContent.isBlank()) {
            _message.value = "모든 항목을 채워주세요!"
            return
        }
        Thread {
            diaryDao.updateItem(DiaryEntity(id, uriInfo.toString(), itemTitle, itemContent))
        }.start()
        _message.value = "수정되었습니다."
        makeMap(uriInfo, itemTitle, itemContent)
    }

    private fun makeMap(uriInfo: Uri?, itemTitle: String, itemContent: String) {
        val map: HashMap<String, String> = hashMapOf(
            "image" to uriInfo.toString(),
            "title" to itemTitle,
            "content" to itemContent
        )
        _map.value = map
    }


}