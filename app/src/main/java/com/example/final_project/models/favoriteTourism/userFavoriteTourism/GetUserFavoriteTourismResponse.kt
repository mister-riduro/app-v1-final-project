package com.example.final_project.models.favoriteTourism.userFavoriteTourism

import com.google.gson.annotations.SerializedName

data class GetUserFavoriteTourismResponse(
    @SerializedName("data")
    val data: List<UserFavoriteTourism>
)
