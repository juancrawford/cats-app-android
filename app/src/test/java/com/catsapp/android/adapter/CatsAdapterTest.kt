package com.catsapp.android.adapter

import android.content.Intent
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingData
import com.catsapp.android.data.MockData.mockCat1
import com.catsapp.android.data.MockData.mockCat2
import com.catsapp.android.presentation.detail.DetailActivity
import com.catsapp.android.presentation.main.CatItemViewModel
import com.catsapp.android.presentation.main.CatsAdapter
import com.catsapp.android.presentation.main.MainActivity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config
class CatsAdapterTest {

    private lateinit var adapter: CatsAdapter
    private lateinit var activity: AppCompatActivity
    private lateinit var parent: ViewGroup

    // Test dispatcher for coroutines
    private val testDispatcher = UnconfinedTestDispatcher()

    /**
     * Set up the test environment before each test.
     */
    @Before
    fun setUp() {
        // Set the main dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)
        // Initialize the CatsAdapter
        adapter = CatsAdapter()
        // Build and setup the MainActivity using Robolectric
        val activityController: ActivityController<MainActivity> =
            Robolectric.buildActivity(MainActivity::class.java).setup()
        // Get the activity instance
        activity = activityController.get()
        // Initialize a FrameLayout as the parent ViewGroup
        parent = FrameLayout(activity)
    }

    /**
     * Reset the main dispatcher after each test.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Test that onCreateViewHolder correctly creates a ViewHolder.
     */
    @Test
    fun `test onCreateViewHolder`() {
        val viewHolder = adapter.onCreateViewHolder(parent, 0)
        assertThat(viewHolder).isNotNull()
    }

    /**
     * Test that onBindViewHolder correctly binds data to the ViewHolder.
     */
    @Test
    fun `test onBindViewHolder`() = runTest {
        // Create a CatItemViewModel with mock data
        val itemViewModel = CatItemViewModel(mockCat1)
        // Submit data to the adapter
        adapter.submitData(activity.lifecycle, PagingData.from(listOf(itemViewModel)))

        // Create and bind the ViewHolder
        val viewHolder = adapter.onCreateViewHolder(parent, 0)
        adapter.onBindViewHolder(viewHolder, 0)

        // Verify that the ImageView is not null
        assertThat(viewHolder.binding.ivCat).isNotNull()
    }

    /**
     * Test that clicking an item opens the DetailActivity with the correct data.
     */
    @Test
    fun `test item click opens detail activity`() = runTest {
        // Create a CatItemViewModel with mock data
        val itemViewModel = CatItemViewModel(mockCat2)
        // Submit data to the adapter
        adapter.submitData(activity.lifecycle, PagingData.from(listOf(itemViewModel)))

        // Create and bind the ViewHolder
        val viewHolder = adapter.onCreateViewHolder(parent, 0)
        adapter.onBindViewHolder(viewHolder, 0)

        // Simulate a click on the main container
        viewHolder.binding.cvMainContainer.performClick()
        // Run any pending tasks in the UI thread
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()

        // Create the expected intent
        val expectedIntent = Intent(activity, DetailActivity::class.java)
        expectedIntent.putExtra(DetailActivity.EXTRA_CAT, mockCat2)
        // Get the actual intent started by the activity
        val actualIntent = Shadows.shadowOf(activity).nextStartedActivity
        // Verify that the actual intent matches the expected intent
        assertThat(actualIntent.component).isEqualTo(expectedIntent.component)
    }
}