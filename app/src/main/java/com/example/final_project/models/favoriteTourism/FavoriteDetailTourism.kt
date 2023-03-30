package com.example.final_project.models.favoriteTourism

import com.example.final_project.models.dto.TourismFacilitiesResponse
import com.google.gson.annotations.SerializedName

data class FavoriteDetailTourism(
    @SerializedName("tourism_id")
    val tourismID: Long,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("tourism_name")
    val tourismName: String,

    @SerializedName("tourism_address")
    val tourismAddress: String,

    @SerializedName("tourism_image")
    val tourismImage: String,

    @SerializedName("tourism_type")
    val tourismType: String,

    @SerializedName("tourism_city")
    val tourismCity: String,

    @SerializedName("province_name")
    val provinceName: String,

    @SerializedName("open_hour")
    val openHour: String,

    @SerializedName("close_hour")
    val closeHour: String,

    @SerializedName("tourism_description")
    val tourismDescription: String,

    @SerializedName("entry_price")
    val entryPrice: Long,

    @SerializedName("traveling_time")
    val travelingTime: String,

    @SerializedName("road_condition")
    val roadCondition: String,

    @SerializedName("tourism_rating")
    val tourismRating: Double,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("facilities")
    val facilities: List<Long?>,

    @SerializedName("routes")
    val routes: List<Long?>
)
