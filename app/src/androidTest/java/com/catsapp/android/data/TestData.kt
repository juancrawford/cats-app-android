package com.catsapp.android.data

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import com.catsapp.android.model.Breed
import com.catsapp.android.model.Cat
import com.catsapp.android.model.Weight

object TestData {
    // Sample Cat data for testing
    val cat = Cat(
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

    // Loading State definition
    val loadingState = CombinedLoadStates(
        refresh = LoadState.Loading,
        prepend = LoadState.NotLoading(endOfPaginationReached = false),
        append = LoadState.NotLoading(endOfPaginationReached = false),
        source = LoadStates(
            refresh = LoadState.Loading,
            prepend = LoadState.NotLoading(endOfPaginationReached = false),
            append = LoadState.NotLoading(endOfPaginationReached = false),
        )
    )

    // Error State definition
    val errorState = CombinedLoadStates(
        refresh = LoadState.Error(Exception("Error")),
        prepend = LoadState.NotLoading(endOfPaginationReached = false),
        append = LoadState.NotLoading(endOfPaginationReached = false),
        source = LoadStates(
            refresh = LoadState.Error(Exception("Error")),
            prepend = LoadState.NotLoading(endOfPaginationReached = false),
            append = LoadState.NotLoading(endOfPaginationReached = false),
        )
    )

    // Not Loading State definition
    val notLoadingState = CombinedLoadStates(
        refresh = LoadState.NotLoading(endOfPaginationReached = false),
        prepend = LoadState.NotLoading(endOfPaginationReached = false),
        append = LoadState.NotLoading(endOfPaginationReached = false),
        source = LoadStates(
            refresh = LoadState.NotLoading(endOfPaginationReached = false),
            prepend = LoadState.NotLoading(endOfPaginationReached = false),
            append = LoadState.NotLoading(endOfPaginationReached = false),
        )
    )
}