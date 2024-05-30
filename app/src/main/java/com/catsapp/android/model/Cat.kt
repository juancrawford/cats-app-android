package com.catsapp.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents a cat image with associated metadata. It is Parcelable to facilitate
 * easy passing between Android components.
 */
@Parcelize
data class Cat(
    val breeds: List<Breed>?,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
) : Parcelable