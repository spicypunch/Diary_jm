package com.example.sunflower_jm.update

interface UpdateContract {
    interface View {
        fun makeToast(message: String)
    }

    interface Presenter {
        fun updateContent()
    }
}