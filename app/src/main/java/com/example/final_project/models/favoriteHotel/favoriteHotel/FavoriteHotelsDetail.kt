package com.example.final_project.models.favoriteHotel.favoriteHotel

import com.google.gson.annotations.SerializedName


data class FavoriteHotelsDetail(
    @SerializedName("hotel_id")
    val hotelID: Long,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("property_type")
    val propertyType: String,

    @SerializedName("hotel_city")
    val hotelCity: String,

    @SerializedName("hotel_province")
    val hotelProvince: String,

    @SerializedName("hotel_address")
    val hotelAddress: String,

    @SerializedName("hotel_rating")
    val hotelRating: String,

    @SerializedName("min_price")
    val minPrice: Long,

    @SerializedName("max_price")
    val maxPrice: Long,

    @SerializedName("latitude")
    val latitude: Long,

    @SerializedName("longitude")
    val longitude: Long,

    @SerializedName("cluster")
    val cluster: Long,

    @SerializedName("hotel_image")
    val hotelImage: String,

    @SerializedName("hotel_name")
    val hotelName: String,

    @SerializedName("facilities")
    val facilities: List<Long>
)
