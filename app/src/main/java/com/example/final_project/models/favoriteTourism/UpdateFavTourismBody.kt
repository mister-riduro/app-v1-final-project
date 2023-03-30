package com.example.final_project.models.favoriteTourism

import com.google.gson.annotations.SerializedName

data class UpdateFavTourismBody(
        @SerializedName("tourisms")
        val tourisms: List<FavoriteTourismID>
)