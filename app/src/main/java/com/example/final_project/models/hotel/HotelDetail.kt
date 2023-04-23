package com.example.final_project.models.hotel

import com.google.gson.annotations.SerializedName

data class HotelDetail(
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

    @SerializedName("province_name")
    val provinceName: String,

    @SerializedName("hotel_address")
    val hotelAddress: String,

    @SerializedName("hotel_rating")
    val hotelRating: Double,

    @SerializedName("min_price")
    val minPrice: Long,

    @SerializedName("max_price")
    val maxPrice: Long,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("cluster")
    val cluster: Long,

    @SerializedName("hotel_image")
    val hotelImage: String,

    @SerializedName("hotel_name")
    val hotelName: String,

    @SerializedName("facilities")
    val facilities: List<HotelDetailFacilitiesResponse>
)
