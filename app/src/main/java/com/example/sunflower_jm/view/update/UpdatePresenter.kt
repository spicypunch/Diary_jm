package com.example.sunflower_jm.view.update

import android.net.Uri
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.model.DiaryEntity

class UpdatePresenter(
    private val diaryDao: DiaryDao,
    private val view: UpdateContract.View
) : UpdateContract.Presenter {

    private var id: Int? = null

    private var uriInfo: Uri? = null

    override fun updateItem(item: DiaryEntity) {
        id = item.id

        view.updateItem(
            title = item.title,
            content = item.content,
            image = item.image,
        )
    }

    override fun updateUri(uriInfo: Uri) {
        this.uriInfo = uriInfo
    }

    override fun updateContent(itemTitle: String, itemContent: String) {
        if (itemTitle.isBlank() || itemContent.isBlank()) {
            view.showToast("모든 항목을 채워주세요!")
            return
        }

        Thread {
            diaryDao.updateItem(DiaryEntity(id, uriInfo.toString(), itemTitle, itemContent))
        }.start()
        view.showToast("수정되었습니다.")
        makeMap(uriInfo, itemTitle, itemContent)
    }

    private fun makeMap(uriInfo: Uri?, itemTitle: String, itemContent: String) {
        val map: HashMap<String, String> = hashMapOf(
            "image" to uriInfo.toString(),
            "title" to itemTitle,
            "content" to itemContent
        )
        view.finish(map)
    }


}