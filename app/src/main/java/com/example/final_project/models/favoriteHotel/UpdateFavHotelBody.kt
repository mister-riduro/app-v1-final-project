package com.example.final_project.models.favoriteHotel

import com.google.gson.annotations.SerializedName


data class UpdateFavHotelBody(
    @SerializedName("hotels")
    val hotels: List<FavoriteHotelID>
)