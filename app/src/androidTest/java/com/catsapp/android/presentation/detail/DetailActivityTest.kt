package com.catsapp.android.presentation.detail

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.catsapp.android.R
import com.catsapp.android.data.TestData.cat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test class for DetailActivity.
 * This class contains UI tests to verify the behavior of DetailActivity.
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class DetailActivityTest {

    // Intent containing the Cat data
    private val intent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java).apply {
        putExtra(DetailActivity.EXTRA_CAT, cat)
    }

    // Rule to handle dependencies injection before each test
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // Rule to launch DetailActivity with the intent
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<DetailActivity> = ActivityScenarioRule(intent)

    /**
     * Setup method to initialize dependencies injection before each test.
     */
    @Before
    fun setup() {
        hiltRule.inject()
    }

    /**
     * Test to verify that UI elements are displayed in DetailActivity.
     */
    @Test
    fun testDetailActivityDisplayed() {
        onView(withId(R.id.appBar)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.image)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
    }

    /**
     * Test to verify that the Cat's description is displayed in DetailActivity.
     */
    @Test
    fun testCatDescriptionDisplayed() {
        onView(withText(cat.breeds?.firstOrNull()?.description)).check(matches(isDisplayed()))
    }
}
