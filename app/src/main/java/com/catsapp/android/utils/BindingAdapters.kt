package com.catsapp.android.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Object containing binding adapters for image loading with Glide.
 */
object BindingAdapters {

    /**
     * Binds an image URL to an ImageView using Glide for image loading.
     *
     * This binding adapter is used to load an image from the specified URL into the ImageView
     * using the Glide library for efficient image loading and caching.
     *
     * @param imgView The ImageView to load the image into.
     * @param imgUrl The URL of the image to load.
     */
    @BindingAdapter("bindImage")
    @JvmStatic
    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)
        }
    }
}