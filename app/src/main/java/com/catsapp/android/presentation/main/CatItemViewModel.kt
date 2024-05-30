package com.catsapp.android.presentation.main

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.catsapp.android.model.Cat

/**
 * ViewModel for an item in the cat list.
 *
 * This ViewModel class represents an item in the cat list, providing properties for
 * the image URL, title, and subtitle of the cat.
 *
 * @param cat The Cat object representing the cat data for this item.
 */
class CatItemViewModel(
    val cat: Cat
) : BaseObservable() {

    /**
     * The URL of the cat image.
     */
    @get:Bindable
    var imageUrl: String = cat.url

    /**
     * The title of the cat.
     */
    @get:Bindable
    var title: String = cat.breeds?.firstOrNull()?.name.orEmpty()

    /**
     * The subtitle of the cat.
     */
    @get:Bindable
    var subTitle: String = cat.breeds?.firstOrNull()?.description.orEmpty()
}