package com.example.final_project.models.favoriteHotel.favoriteHotelNew


import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("new_favorite_hotel_favorite_id")
    val newFavoriteHotelFavoriteId: Int,
    @SerializedName("newhotels_hotel_id")
    val newhotelsHotelId: NewhotelsHotelId
)