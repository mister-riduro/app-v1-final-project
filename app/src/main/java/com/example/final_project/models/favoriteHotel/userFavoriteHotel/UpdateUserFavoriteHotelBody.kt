package com.example.final_project.models.favoriteHotel.userFavoriteHotel

import com.google.gson.annotations.SerializedName

data class UpdateUserFavoriteHotelBody(
    @SerializedName("is_favorite")
    val isFavorite: Boolean
)
