package com.example.final_project.models.favoriteHotel.favoriteHotelNew


import com.google.gson.annotations.SerializedName

data class NewFavoriteHotelResponse(
    @SerializedName("data")
    val `data`: List<Data>
)