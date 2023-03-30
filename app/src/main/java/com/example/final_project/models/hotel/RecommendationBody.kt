package com.example.final_project.models.hotel

import com.google.gson.annotations.SerializedName

data class RecommendationBody(
    @SerializedName("facilities")
    val facilities: ArrayList<String>,

    @SerializedName("hotel_rating")
    val hotelRating: Double
)
