package com.catsapp.android.domain

import com.catsapp.android.data.APIs
import javax.inject.Inject

/**
 * Use case class for fetching a list of cat images.
 *
 * This class interacts with the APIs interface to retrieve data.
 *
 * @property apIs The APIs interface for making network requests.
 */
class BreedUseCase @Inject constructor(
    private val apIs: APIs
) {
    /**
     * Fetches a list of cat images from the server.
     *
     * This method calls the getCats method from the APIs interface to fetch
     * the data.
     *
     * @param page The page number to retrieve. This is used for pagination.
     * @return A [Response] object containing a list of [Cat] objects.
     */
    suspend fun getCatsList(page: Int) = apIs.getCats(page)
}