package com.example.final_project.models.favoriteTourism

import com.google.gson.annotations.SerializedName

data class CreateFavoriteTourismBody(
    @SerializedName("user_id")
    val userID : String,

    @SerializedName("tourisms")
    val tourisms: List<Long>
)
