package com.example.final_project.models.favoriteTourism

import com.google.gson.annotations.SerializedName

data class FavoriteTourismDataResponse(
    @SerializedName("data")
    val data: List<FavoriteTourismResponse>
)
