package com.catsapp.android.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.catsapp.android.domain.BreedUseCase
import com.catsapp.android.model.Cat
import retrofit2.HttpException

/**
 * A PagingSource implementation for loading cat images.
 *
 * This class uses the BreedUseCase to fetch cat images from a paginated API.
 *
 * @property breedUseCase The use case for fetching cat data.
 */
class CatsPagingSource (
    private val breedUseCase: BreedUseCase,
) : PagingSource<Int, Cat>() {

    /**
     * Loads a page of cat data.
     *
     * This method fetches the current page of cat images using the BreedUseCase.
     * It handles pagination and returns the result in a LoadResult.Page or LoadResult.Error.
     *
     * @param params The parameters for loading the data, including the key for the current page.
     * @return A LoadResult containing either a page of cat data or an error.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        return try {
            val currentPage = params.key ?: 1
            val response = breedUseCase.getCatsList(currentPage)
            val data = mutableListOf<Cat>()
            data.addAll(response.body()!!)

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    /**
     * Returns the key for the page to be loaded after a refresh.
     *
     * This method determines the key for the page to be loaded based on the current paging state.
     *
     * @param state The current state of the paging system.
     * @return The key for the page to be loaded, or null if it cannot be determined.
     */
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}