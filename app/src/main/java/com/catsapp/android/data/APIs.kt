package com.catsapp.android.data

import com.catsapp.android.model.Cat
import com.catsapp.android.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIs {

    /**
     * Retrieves a list of cat images from the server.
     *
     * This method makes a GET request to the "images/search" endpoint to fetch cat images.
     * The request headers include the Content-Type, Accept type, and API key for authorization.
     *
     * @param page The page number to retrieve. This is used for pagination.
     * @param limit The number of cat images to retrieve per page. Defaults to 10 if not specified.
     * @param hasBreeds Indicates whether the retrieved images should include breed information.
     *                  A value of 1 means breed information is required. Defaults to 1 if not specified.
     * @return A [Response] object containing a list of [Cat] objects.
     */
    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json",
        "x-api-key: " + Constants.API_KEY
    )
    @GET("images/search")
    suspend fun getCats(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10,
        @Query("has_breeds") hasBreeds: Int = 1
    ): Response<List<Cat>>
}