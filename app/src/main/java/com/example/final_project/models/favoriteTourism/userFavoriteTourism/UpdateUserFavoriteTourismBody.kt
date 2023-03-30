package com.example.final_project.models.favoriteTourism.userFavoriteTourism

import com.google.gson.annotations.SerializedName

data class UpdateUserFavoriteTourismBody(
    @SerializedName("is_favorite")
    val isFavorite: Boolean
)
