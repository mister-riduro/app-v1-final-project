package com.example.final_project.models.favoriteHotel

import com.google.gson.annotations.SerializedName


data class UpdateFavoriteHotelBody(
    @SerializedName("hotels")
    val hotels: List<FavoriteHotelID>
)