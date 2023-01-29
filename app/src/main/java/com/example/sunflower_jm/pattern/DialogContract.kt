package com.example.sunflower_jm.pattern

import android.content.Context
import android.net.Uri
import androidx.constraintlayout.widget.ConstraintLayout

interface DialogContract {
    interface View {
        fun setUri(uri: Uri?)
    }

    interface Presenter {
        fun openDialog(context: Context)
    }
}