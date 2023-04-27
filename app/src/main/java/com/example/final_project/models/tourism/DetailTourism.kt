package com.example.final_project.models.tourism

import com.example.final_project.models.Routes
import com.example.final_project.models.dto.TourismFacilitiesResponse
import com.example.final_project.models.tourism.tourismImage.ImageData
import com.google.gson.annotations.SerializedName

data class DetailTourism(
    @SerializedName("tourism_id")
    val tourismID: Int,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("tourism_name")
    val tourismName: String,

    @SerializedName("tourism_address")
    val tourismAddress: String,

    @SerializedName("tourism_image")
    val tourismImage: ImageData,

    @SerializedName("tourism_type")
    val tourismType: String,

    @SerializedName("tourism_city")
    val tourismCity: String,

    @SerializedName("tourism_province")
    val tourismProvince: String,

    @SerializedName("operational_hour")
    val operationalHour: String,

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
    val facilities: List<TourismFacilitiesResponse?>,

    @SerializedName("routes")
    val routes: List<Routes?>
)
