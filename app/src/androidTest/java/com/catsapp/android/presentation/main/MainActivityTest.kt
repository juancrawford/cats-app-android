package com.catsapp.android.presentation.main

import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.catsapp.android.R
import com.catsapp.android.data.TestData.errorState
import com.catsapp.android.data.TestData.loadingState
import com.catsapp.android.data.TestData.notLoadingState
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@LargeTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

    // Rule to handle dependencies injection before each test
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // Rule to launch the MainActivity before each test
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var catsAdapter: CatsAdapter

    private val loadStateFlow = MutableStateFlow(notLoadingState)

    /**
     * Setup method to initialize Adapter before each test.
     */
    @Before
    fun setup() {
        hiltRule.inject()
    }

    /**
     * Test to check if the MainActivity launches successfully.
     */
    @Test
    fun testActivityLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertThat(activity).isNotNull()
            }
        }
    }

    /**
     * Test to check if the RecyclerView is visible.
     */
    @Test
    fun testRecyclerViewIsVisible() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val recyclerView: RecyclerView = activity.findViewById(R.id.rvCats)
                assertThat(recyclerView.visibility).isEqualTo(View.VISIBLE)
            }
        }
    }

    /**
     * Test case to verify if data is displayed in the RecyclerView.
     */
    @Test
    fun testDataDisplayedInRecyclerView() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val recyclerView: RecyclerView = activity.findViewById(R.id.rvCats)
                assertThat(recyclerView.adapter).isNotNull()
                assertThat(recyclerView.adapter!!.itemCount).isNotEqualTo(0)
            }
        }
    }

    /**
     * Test to check the visibility of the progress bar based on load state.
     */
    @Test
    fun testProgressBarVisibility() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val progressBar: View = activity.findViewById(R.id.pbProgress)
                val retryButton: View = activity.findViewById(R.id.tvRetry)

                activity.lifecycleScope.launch {
                    loadStateFlow.collectLatest { loadState ->
                        when (loadState.refresh) {
                            is LoadState.Loading -> {
                                delay(100)
                                assertThat(progressBar.visibility).isEqualTo(View.VISIBLE)
                                assertThat(retryButton.visibility).isEqualTo(View.GONE)
                            }
                            is LoadState.Error -> {
                                delay(100)
                                assertThat(progressBar.visibility).isEqualTo(View.GONE)
                                assertThat(retryButton.visibility).isEqualTo(View.VISIBLE)
                            }
                            is LoadState.NotLoading -> {
                                delay(100)
                                assertThat(progressBar.visibility).isEqualTo(View.GONE)
                                assertThat(retryButton.visibility).isEqualTo(View.GONE)
                            }
                        }
                    }
                }

                // Simulate different load states
                simulateLoadStates()
            }
        }
    }

    /**
     * Test to check the retry button click when the app could not get the
     * server response.
     */
    @Test
    fun testRetryButtonClick() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val retryButton: View = activity.findViewById(R.id.tvRetry)
                val progressBar: View = activity.findViewById(R.id.pbProgress)

                activity.lifecycleScope.launch {
                    loadStateFlow.collectLatest { loadState ->
                        when (loadState.refresh) {
                            is LoadState.Loading -> {
                                delay(100)
                                assertThat(progressBar.visibility).isEqualTo(View.VISIBLE)
                                assertThat(retryButton.visibility).isEqualTo(View.GONE)
                            }
                            is LoadState.Error -> {
                                retryButton.performClick()
                            }
                            is LoadState.NotLoading -> {}
                        }
                    }
                }

                // Simulate Error State
                loadStateFlow.value = errorState
            }
        }
    }

    private fun simulateLoadStates() {
        // Simulate Loading State
        loadStateFlow.value = loadingState
        // Simulate Error State
        loadStateFlow.value = errorState
        // Simulate Not Loading State
        loadStateFlow.value = notLoadingState
    }
}