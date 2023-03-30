package com.example.final_project.models.favoriteTourism

import com.google.gson.annotations.SerializedName

data class FavoriteTourism(
    @SerializedName("id")
    val id: Long,

    @SerializedName("favorite_tourism_favorite_id")
    val favoriteTourismFavoriteID: Long,

    @SerializedName("tourisms_tourism_id")
    val tourismsTourismID: FavoriteDetailTourism
)
