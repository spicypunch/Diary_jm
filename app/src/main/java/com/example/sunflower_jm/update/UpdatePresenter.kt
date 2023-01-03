package com.example.sunflower_jm.update

import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.DiaryEntity

class UpdatePresenter(
    private val id: Int,
    private val itemTitle: String,
    private val itemContent: String,
    private val diaryDao: DiaryDao,
    private val view: UpdateContract.View
) : UpdateContract.Presenter {

    override fun updateContent() {
        if (itemTitle.isBlank() || itemContent.isBlank()) {
            view.makeToast("모든 항목을 채워주세요!")
        } else {
            Thread {
                diaryDao.updateItem(DiaryEntity(id, itemTitle, itemContent))
            }.start()
            view.makeToast("수정되었습니다.")
        }
    }

}