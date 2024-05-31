package com.catsapp.android.domain

import com.catsapp.android.data.MockData.mockCats
import com.catsapp.android.data.APIs
import com.catsapp.android.domain.BreedUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class BreedUseCaseTest {

    // Mock object for the APIs class
    @Mock
    private lateinit var mockApi: APIs

    // Instance of BreedUseCase to be tested
    private lateinit var breedUseCase: BreedUseCase

    /**
     * Sets up the necessary objects before each test.
     */
    @Before
    fun setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)
        // Initialize the BreedUseCase with the mocked APIs
        breedUseCase = BreedUseCase(mockApi)
    }

    /**
     * Tests that the getCatsList method returns a list of cats when the network request is successful.
     */
    @Test
    fun `getCatsList should return cat list`(): Unit = runBlocking {
        // Mock response with a list of cats
        val mockResponse = Response.success(mockCats)

        // Set up mock behavior for the APIs
        `when`(mockApi.getCats(1)).thenReturn(mockResponse)

        // Call the getCatsList method
        val response = breedUseCase.getCatsList(1)

        // Verify the response is successful and contains the correct data
        assert(response.isSuccessful)
        assert(response.body() == mockCats)
        // Verify the getCats method was called on the mockApi
        verify(mockApi).getCats(1)
    }
}