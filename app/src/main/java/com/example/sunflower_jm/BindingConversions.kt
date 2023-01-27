package com.example.sunflower_jm

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView : ImageView, uri : String) {
        Glide.with(imageView.context).load(uri)
            .error(R.drawable.error)
            .into(imageView)
    }

}