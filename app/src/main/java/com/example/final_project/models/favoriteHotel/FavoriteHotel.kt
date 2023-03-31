package com.example.final_project.models.favoriteHotel

import com.google.gson.annotations.SerializedName

data class FavoriteHotel(
    @SerializedName("id")
    val id: Long,

    @SerializedName("favorite_hotel_favorite_id")
    val favoriteHotelFavoriteID: Long,

    @SerializedName("hotels_hotel_id")
    val hotelsHotelID: FavoriteDetailHotel
)
