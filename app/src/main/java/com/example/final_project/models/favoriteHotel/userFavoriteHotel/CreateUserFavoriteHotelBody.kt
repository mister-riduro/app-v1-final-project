package com.example.final_project.models.favoriteHotel.userFavoriteHotel

import com.google.gson.annotations.SerializedName

data class CreateUserFavoriteHotelBody (
    @SerializedName("user_id")
    val userID: String,

    @SerializedName("hotel_id")
    val hotelID: Long,

    @SerializedName("is_favorite")
    val isFavorite: Boolean
    )