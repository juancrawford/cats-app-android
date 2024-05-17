package com.catsapp.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Represents the details of a cat breed. It is Parcelable to facilitate easy
 * passing between Android components.
 */
@Parcelize
data class Breed(
    val weight: Weight,
    val id: String,
    val name: String,
    @SerializedName("cfa_url")
    val cfaUrl: String?,
    @SerializedName("vetstreet_url")
    val vetstreetUrl: String?,
    @SerializedName("vcahospitals_url")
    val vcahospitalsUrl: String?,
    val temperament: String?,
    val origin: String?,
    @SerializedName("country_codes")
    val countryCodes: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    val description: String,
    @SerializedName("life_span")
    val lifeSpan: String?,
    val indoor: Int?,
    val lap: Int?,
    @SerializedName("alt_names")
    val altNames: String?,
    val adaptability: Int?,
    @SerializedName("affection_level")
    val affectionLevel: Int?,
    @SerializedName("child_friendly")
    val childFriendly: Int?,
    @SerializedName("dog_friendly")
    val dogFriendly: Int?,
    @SerializedName("energy_level")
    val energyLevel: Int?,
    val grooming: Int?,
    @SerializedName("health_issues")
    val healthIssues: Int?,
    val intelligence: Int?,
    @SerializedName("shedding_level")
    val sheddingLevel: Int?,
    @SerializedName("social_needs")
    val socialNeeds: Int?,
    @SerializedName("stranger_friendly")
    val strangerFriendly: Int?,
    val vocalisation: Int?,
    val experimental: Int?,
    val hairless: Int?,
    val natural: Int?,
    val rare: Int?,
    val rex: Int?,
    @SerializedName("suppressed_tail")
    val suppressedTail: Int?,
    @SerializedName("short_legs")
    val shortLegs: Int?,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String?,
    val hypoallergenic: Int?,
    @SerializedName("reference_image_id")
    val referenceImageId: String?
) : Parcelable