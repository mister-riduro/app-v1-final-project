package com.example.final_project.models.favoriteHotel

import com.google.gson.annotations.SerializedName

data class UpsertFavoriteHotelResponse(
    @SerializedName("favorite_id")
    val favoriteID: Long,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("user_id")
    val userID: String,

    @SerializedName("hotels")
    val hotels: List<Long>
)
