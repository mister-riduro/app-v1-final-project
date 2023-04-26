package com.example.final_project.models.distance


import com.google.gson.annotations.SerializedName

data class DistanceData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("distance_id")
    val distanceId: Int,
    @SerializedName("hotel_id")
    val distanceHotelData: DistanceHotelData,
    @SerializedName("tourism_id")
    val distanceTourismData: DistanceTourismData,
    @SerializedName("updated_at")
    val updatedAt: Any
)