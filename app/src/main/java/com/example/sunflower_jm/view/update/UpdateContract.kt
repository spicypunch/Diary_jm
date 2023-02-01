package com.example.sunflower_jm.view.update

interface UpdateContract {
    interface View {
        fun finishActivity(message: String)

        fun sendResult(map: HashMap<String, String>)
    }

    interface Presenter {
        fun updateContent()

        fun makeMap()
    }
}