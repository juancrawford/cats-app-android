package com.catsapp.android.adapter

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.catsapp.android.presentation.main.LoadMoreAdapter
import com.catsapp.android.presentation.main.MainActivity
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config
class LoadMoreAdapterTest {

    private lateinit var adapter: LoadMoreAdapter
    private lateinit var activity: AppCompatActivity
    private lateinit var parent: ViewGroup
    private lateinit var retryCallback: () -> Unit

    /**
     * Sets up the necessary objects before each test.
     */
    @Before
    fun setUp() {
        // Mock the retry callback
        retryCallback = mock()
        // Initialize the adapter with the mocked retry callback
        adapter = LoadMoreAdapter(retryCallback)

        // Set up the MainActivity using Robolectric
        val activityController: ActivityController<MainActivity> =
            Robolectric.buildActivity(MainActivity::class.java).setup()
        activity = activityController.get()

        // Create a parent ViewGroup (FrameLayout)
        parent = FrameLayout(activity)
    }

    /**
     * Tests the onCreateViewHolder method to ensure the ViewHolder is not null.
     */
    @Test
    fun testOnCreateViewHolder() {
        // Create a new ViewHolder
        val viewHolder = adapter.onCreateViewHolder(parent, LoadState.Loading)
        // Assert that the ViewHolder is not null
        assertNotNull(viewHolder)
    }

    /**
     * Tests the onBindViewHolder method for the Loading state.
     */
    @Test
    fun testOnBindViewHolderLoading() {
        // Create a new ViewHolder
        val viewHolder = adapter.onCreateViewHolder(parent, 0)

        // Bind the ViewHolder with the Loading state
        adapter.onBindViewHolder(viewHolder, LoadState.Loading)

        // Assert that the progress bar is visible and the retry button and error message are hidden
        assertTrue(viewHolder.binding.pbLoadMore.isVisible)
        assertFalse(viewHolder.binding.tvLoadMore.isVisible)
        assertFalse(viewHolder.binding.btnLoadMoreRetry.isVisible)
    }

    /**
     * Tests the onBindViewHolder method for the Error state.
     */
    @Test
    fun testOnBindViewHolderError() {
        // Create a new ViewHolder
        val viewHolder = adapter.onCreateViewHolder(parent, 0)

        // Create an Error state
        val errorState = LoadState.Error(Exception("Network error"))

        // Bind the ViewHolder with the Error state
        adapter.onBindViewHolder(viewHolder, errorState)

        // Assert that the progress bar is hidden and the retry button and error message are visible
        assertFalse(viewHolder.binding.pbLoadMore.isVisible)
        assertTrue(viewHolder.binding.tvLoadMore.isVisible)
        assertTrue(viewHolder.binding.btnLoadMoreRetry.isVisible)
    }

    /**
     * Tests that the retry button invokes the retry callback.
     */
    @Test
    fun testRetryButtonClick() {
        // Create a new ViewHolder
        val viewHolder = adapter.onCreateViewHolder(parent, 0)

        // Bind the ViewHolder with any state (for simplicity, 0)
        adapter.onBindViewHolder(viewHolder, 0)

        // Perform a click on the retry button
        viewHolder.binding.btnLoadMoreRetry.performClick()

        // Verify that the retry callback was invoked
        verify(retryCallback).invoke()
    }
}