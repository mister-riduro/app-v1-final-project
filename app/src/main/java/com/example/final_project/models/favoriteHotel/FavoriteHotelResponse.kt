package com.example.final_project.models.favoriteHotel

import com.google.gson.annotations.SerializedName

data class FavoriteHotelResponse(
    @SerializedName("favorite_id")
    val favoriteID: Long,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("user_id")
    val userID: String,

    @SerializedName("hotels")
    var hotels: List<FavoriteHotel>
)
