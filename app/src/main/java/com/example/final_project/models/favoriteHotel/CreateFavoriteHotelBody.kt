package com.example.final_project.models.favoriteHotel

import com.google.gson.annotations.SerializedName

data class CreateFavoriteHotelBody(
    @SerializedName("user_id")
    val userID: String,

    @SerializedName("hotels")
    val hotels: List<Long>
)
