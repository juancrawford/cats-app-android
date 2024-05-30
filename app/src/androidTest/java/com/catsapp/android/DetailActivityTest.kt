package com.catsapp.android

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.catsapp.android.model.Breed
import com.catsapp.android.model.Cat
import com.catsapp.android.model.Weight
import com.catsapp.android.presentation.detail.DetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test class for DetailActivity.
 * This class contains UI tests to verify the behavior of DetailActivity.
 */
@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    // Sample Cat data for testing
    private val cat = Cat(
        breeds = listOf(
            Breed(
                weight = Weight(
                    imperial = "6 - 15",
                    metric = "3 - 7"
                ),
                id = "birm",
                name = "Birman",
                cfaUrl = "http://cfa.org/Breeds/BreedsAB/Birman.aspx",
                vetstreetUrl = "http://www.vetstreet.com/cats/birman",
                vcahospitalsUrl = "https://vcahospitals.com/know-your-pet/cat-breeds/birman",
                temperament = "Affectionate, Active, Gentle, Social",
                origin = "France",
                countryCodes = "FR",
                countryCode = "FR",
                description = "The Birman is a docile, quiet cat who loves people and will follow them from room to room. Expect the Birman to want to be involved in what you’re doing. He communicates in a soft voice, mainly to remind you that perhaps it’s time for dinner or maybe for a nice cuddle on the sofa. He enjoys being held and will relax in your arms like a furry baby.",
                lifeSpan = "14 - 15",
                indoor = 0,
                lap = 1,
                altNames = "Sacred Birman, Sacred Cat Of Burma",
                adaptability = 5,
                affectionLevel = 5,
                childFriendly = 4,
                dogFriendly = 5,
                energyLevel = 3,
                grooming = 2,
                healthIssues = 1,
                intelligence = 3,
                sheddingLevel = 3,
                socialNeeds = 4,
                strangerFriendly = 3,
                vocalisation = 1,
                experimental = 0,
                hairless = 0,
                natural = 0,
                rare = 0,
                rex = 0,
                suppressedTail = 0,
                shortLegs = 0,
                wikipediaUrl = "https://en.wikipedia.org/wiki/Birman",
                hypoallergenic = 0,
                referenceImageId = "HOrX5gwLS"
            )
        ),
        id = "2LEN_GHmx",
        url = "https://cdn2.thecatapi.com/images/2LEN_GHmx.jpg",
        width = 1600,
        height = 1200
    )

    // Intent containing the Cat data
    private val intent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java).apply {
        putExtra(DetailActivity.EXTRA_CAT, cat)
    }

    // Rule to launch DetailActivity with the intent
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<DetailActivity> = ActivityScenarioRule(intent)

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
