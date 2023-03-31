package com.example.final_project.models.favoriteHotel.userFavoriteHotel

import com.google.gson.annotations.SerializedName

data class UpsertUserFavoriteHotelResponse(
    @SerializedName("data")
    val data: UserFavoriteHotel
)
