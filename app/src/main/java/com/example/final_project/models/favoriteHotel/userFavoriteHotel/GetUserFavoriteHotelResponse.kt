package com.example.final_project.models.favoriteHotel.userFavoriteHotel

import com.google.gson.annotations.SerializedName

data class GetUserFavoriteHotelResponse(
    @SerializedName("data")
    val data: List<UserFavoriteHotel>
)
