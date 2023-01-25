package com.example.sunflower_jm

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : String) {
        Glide.with(imageView.context).load(url)
            .error(R.drawable.error)
            .into(imageView)
    }

}