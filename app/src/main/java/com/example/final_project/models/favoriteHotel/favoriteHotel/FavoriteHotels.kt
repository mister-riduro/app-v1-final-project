package com.example.final_project.models.favoriteHotel.favoriteHotel

import com.google.gson.annotations.SerializedName

data class FavoriteHotels(
    @SerializedName("id")
    val id: Long,

    @SerializedName("new_favorite_hotel_favorite_id")
    val newFavoriteHotelFavoriteID: Long,

    @SerializedName("newhotels_hotel_id")
    val newHotelsHotelID: FavoriteHotelsDetail
)
