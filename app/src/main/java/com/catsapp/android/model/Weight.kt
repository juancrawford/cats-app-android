package com.catsapp.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents the weight information for a cat breed. It is Parcelable to facilitate
 * easy passing between Android components.
 */
@Parcelize
data class Weight(
    val imperial: String,
    val metric: String
) : Parcelable