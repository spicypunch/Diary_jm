package com.example.sunflower_jm.view.update

import android.net.Uri
import com.example.sunflower_jm.db.model.DiaryEntity

interface UpdateContract {
    interface View {
        fun showToast(message: String)

        fun finish(map: HashMap<String, String>)

        fun updateItem(title: String, content: String, image: String?)
    }

    interface Presenter {
        fun updateContent(itemTitle: String, itemContent: String)

        fun updateItem(item: DiaryEntity)

        fun updateUri(uriInfo: Uri)
    }
}