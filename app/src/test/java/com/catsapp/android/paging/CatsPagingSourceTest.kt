package com.catsapp.android.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.catsapp.android.data.MockData.mockCat1
import com.catsapp.android.data.MockData.mockCats
import com.catsapp.android.domain.BreedUseCase
import com.catsapp.android.paging.CatsPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class CatsPagingSourceTest {

    @Mock
    private lateinit var mockBreedUseCase: BreedUseCase

    private lateinit var catsPagingSource: CatsPagingSource

    /**
     * Sets up the necessary objects before each test.
     */
    @Before
    fun setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)
        // Initialize the CatsPagingSource with the mocked BreedUseCase
        catsPagingSource = CatsPagingSource(mockBreedUseCase)
    }

    /**
     * Tests that the load method returns a page when the network request is successful.
     */
    @Test
    fun `load should return a page when successful`() = runBlocking {
        // Mock response with a list of cats
        val mockResponse = Response.success(mockCats)

        // Set up mock behavior for the BreedUseCase
        `when`(mockBreedUseCase.getCatsList(1)).thenReturn(mockResponse)

        // Load the first page
        val loadResult = catsPagingSource.load(PagingSource.LoadParams.Refresh(1, 2, false))

        // Verify the load result is a Page and contains the correct data
        assert(loadResult is PagingSource.LoadResult.Page)
        val page = loadResult as PagingSource.LoadResult.Page
        assert(page.data == mockCats)
        assert(page.prevKey == null)
        assert(page.nextKey == 2)
    }

    /**
     * Tests that the load method returns an error when the network request fails.
     */
    @Test
    fun `load should return error when request fails`() = runBlocking {
        // Set up mock behavior to throw an exception
        `when`(mockBreedUseCase.getCatsList(1)).thenThrow(RuntimeException("Network error"))

        // Attempt to load the first page
        val loadResult = catsPagingSource.load(PagingSource.LoadParams.Refresh(1, 2, false))

        // Verify the load result is an Error and contains the correct exception
        assert(loadResult is PagingSource.LoadResult.Error)
        val error = loadResult as PagingSource.LoadResult.Error
        assert(error.throwable is RuntimeException)
        assert(error.throwable.message == "Network error")
    }

    /**
     * Tests that the getRefreshKey method returns the correct key based on the PagingState.
     */
    @Test
    fun `getRefreshKey should return correct key`() {
        // Create a mock PagingState with a single page of data
        val state = PagingState(
            pages = listOf(PagingSource.LoadResult.Page(data = listOf(mockCat1), prevKey = null, nextKey = 2)),
            anchorPosition = null,
            config = PagingConfig(10),
            leadingPlaceholderCount = 0
        )

        // Get the refresh key from the CatsPagingSource
        val refreshKey = catsPagingSource.getRefreshKey(state)

        // Verify the refresh key is correct
        assert(refreshKey == null)
    }
}