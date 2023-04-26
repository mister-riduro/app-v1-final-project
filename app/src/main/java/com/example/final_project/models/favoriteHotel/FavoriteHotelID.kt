package com.example.final_project.models.favoriteHotel

import com.google.gson.annotations.SerializedName

data class FavoriteHotelID(
    @SerializedName("newhotels_hotel_id")
    val hotelsHotelID: Long
)
