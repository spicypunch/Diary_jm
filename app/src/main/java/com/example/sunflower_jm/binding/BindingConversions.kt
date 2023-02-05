package com.example.sunflower_jm.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.sunflower_jm.R

object BindingConversions {

    @BindingAdapter("imageUri")
    @JvmStatic
    fun loadImage(imageView: ImageView, uri: String?) {
        if (uri != null) {
            Glide.with(imageView.context)
                .load(uri)
                .error(R.drawable.error)
                .placeholder(R.drawable.loading)
                .into(imageView)
        }
    }
}
