package com.example.final_project.models.distance


import com.google.gson.annotations.SerializedName

data class DistanceTourismData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("entry_price")
    val entryPrice: String,
    @SerializedName("facilities")
    val facilities: List<Int>,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("operational_hour")
    val operationalHour: String,
    @SerializedName("road_condition")
    val roadCondition: String,
    @SerializedName("routes")
    val routes: List<Any>,
    @SerializedName("tourism_address")
    val tourismAddress: String,
    @SerializedName("tourism_city")
    val tourismCity: String,
    @SerializedName("tourism_description")
    val tourismDescription: String,
    @SerializedName("tourism_id")
    val tourismId: Int,
    @SerializedName("tourism_image")
    val tourismImage: Any,
    @SerializedName("tourism_name")
    val tourismName: String,
    @SerializedName("tourism_province")
    val tourismProvince: String,
    @SerializedName("tourism_rating")
    val tourismRating: Double,
    @SerializedName("tourism_type")
    val tourismType: String,
    @SerializedName("traveling_time")
    val travelingTime: String,
    @SerializedName("updated_at")
    val updatedAt: Any
)