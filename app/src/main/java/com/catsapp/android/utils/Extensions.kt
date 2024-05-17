package com.catsapp.android.utils

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable

/**
 * Retrieves a parcelable extra from the Intent using the specified key.
 *
 * This function is an extension function for Intent class that simplifies the process of retrieving
 * parcelable extras from Intents. It supports retrieving parcelables of any type that implements
 * the Parcelable interface.
 *
 * @param key The key associated with the parcelable extra.
 * @return The parcelable extra associated with the specified key, or null if not found or if
 * the parcelable cannot be casted to the expected type.
 * @see Parcelable
 */
inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}