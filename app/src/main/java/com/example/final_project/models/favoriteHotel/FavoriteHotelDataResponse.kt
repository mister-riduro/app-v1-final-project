package com.example.final_project.models.favoriteHotel

import com.google.gson.annotations.SerializedName

data class FavoriteHotelDataResponse(
    @SerializedName("data")
    val data: List<FavoriteHotelResponse>
)
