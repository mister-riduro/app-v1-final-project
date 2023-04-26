package com.example.final_project.models.distance


import com.google.gson.annotations.SerializedName

data class DistanceHotelData(
    @SerializedName("cluster")
    val cluster: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("facilities")
    val facilities: List<Int>,
    @SerializedName("hotel_address")
    val hotelAddress: String,
    @SerializedName("hotel_city")
    val hotelCity: String,
    @SerializedName("hotel_id")
    val hotelId: Int,
    @SerializedName("hotel_image")
    val hotelImage: String,
    @SerializedName("hotel_link")
    val hotelLink: String,
    @SerializedName("hotel_name")
    val hotelName: String,
    @SerializedName("hotel_province")
    val hotelProvince: String,
    @SerializedName("hotel_rating")
    val hotelRating: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("map_link")
    val mapLink: String,
    @SerializedName("max_price")
    val maxPrice: String,
    @SerializedName("min_price")
    val minPrice: String,
    @SerializedName("property_type")
    val propertyType: String,
    @SerializedName("updated_at")
    val updatedAt: Any
)