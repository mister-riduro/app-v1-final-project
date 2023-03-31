package com.example.final_project.models.favoriteHotel.userFavoriteHotel

import com.google.gson.annotations.SerializedName

data class UserFavoriteHotel(
    @SerializedName("id")
    val id: Long,

    @SerializedName("date_created")
    val dateCreated: String,

    @SerializedName("date_updated")
    val dateUpdated: String,

    @SerializedName("user_id")
    val userID: String,

    @SerializedName("hotel_id")
    val hotelID: Long,

    @SerializedName("is_favorite")
    val isFavorite: Boolean
)
