package com.example.final_project.models.favoriteHotel.favoriteHotelNew


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("favorite_id")
    val favoriteId: Int,
    @SerializedName("hotels")
    val hotels: List<Hotel>,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String
)