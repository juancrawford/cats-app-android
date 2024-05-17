package com.catsapp.android

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.catsapp.android.presentation.main.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test class for testing MainActivity.
 *
 * This class contains test cases to verify the functionality and behavior of the MainActivity
 * class using Espresso testing framework.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    /**
     * Rule to launch the MainActivity under test.
     */
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Test case to verify if the RecyclerView is visible on the screen.
     */
    @Test
    fun testRecyclerViewVisible() {
        onView(withId(R.id.rvCats)).check(matches(isDisplayed()))
    }

    /**
     * Test case to verify if data is displayed in the RecyclerView.
     * It waits for data to be loaded, checks if the RecyclerView has at least one item,
     * scrolls to the last item of the first page, and checks if the item has any text.
     */
    @Test
    fun testDataDisplayedInRecyclerView() {
        // Wait for data to be loaded
        Thread.sleep(3000) // Adjust this delay according to your app's loading time

        // Check if RecyclerView has at least one item
        onView(withId(R.id.rvCats)).check(matches(hasMinimumChildCount(1)))

        // Scroll RecyclerView to the last item and check if it's displayed
        onView(withId(R.id.rvCats)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9)
        )

        // Check if the last item has any text
        onView(withId(R.id.rvCats)).check(
            matches(
                atPosition(
                    9, // Position of the last item of the first page
                    hasDescendant(
                        anyOf(
                            instanceOf(TextView::class.java),
                            withText(Matchers.not(""))
                        )
                    )
                )
            )
        )
    }

    /**
     * Custom matcher to match a view at a specific position in the RecyclerView.
     *
     * @param position The position of the view in the RecyclerView.
     * @param itemMatcher The matcher for the view.
     * @return A Matcher<View> object.
     */
    @Suppress("SameParameterValue")
    private fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {
                return view != null && itemMatcher.matches((view as RecyclerView).findViewHolderForAdapterPosition(position)?.itemView)
            }
        }
    }
}
