package com.example.final_project.models.favoriteTourism.userFavoriteTourism

import com.google.gson.annotations.SerializedName

data class UserFavoriteTourism(
    @SerializedName("id")
    val id: Long,

    @SerializedName("date_created")
    val dateCreated: String,

    @SerializedName("date_updated")
    val dateUpdated: String,

    @SerializedName("user_id")
    val userID: String,

    @SerializedName("tourism_id")
    val tourismID: Long,

    @SerializedName("is_favorite")
    val isFavorite: Boolean
)
