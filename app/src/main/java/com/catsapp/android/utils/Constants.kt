package com.catsapp.android.utils

/**
 * An object that holds constant values used throughout the application.
 */
object Constants {
    /**
     * The base URL for the Cat API.
     */
    const val BASE_URL = "https://api.thecatapi.com/v1/"

    /**
     * The API key for accessing the Cat API.
     */
    const val API_KEY = "live_yOs5YsznTrkcYBfckg2m9OnIENkJC0QJvgDCwmuNuGT7VYrXZpxhHVs1xS6xYiC3"

    /**
     * The connection timeout duration in seconds.
     */
    const val CONNECTION_TIMEOUT = 10

    /**
     * The read timeout duration in seconds.
     */
    const val READ_TIMEOUT = 30

    /**
     * The write timeout duration in seconds.
     */
    const val WRITE_TIMEOUT = 30

    /**
     * The cache size in bytes.
     */
    const val CACHE_SIZE_BYTES = 10 * 1024 * 1024L
}
