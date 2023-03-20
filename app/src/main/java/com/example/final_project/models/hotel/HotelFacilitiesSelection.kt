package com.example.final_project.models.hotel

import com.google.gson.annotations.SerializedName

data class HotelFacilitiesSelection(
    @SerializedName("hfacilities_id")
    val hfacilitiesId: Long,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("facilities_name")
    val facilitiesName: String,

    @SerializedName("facilities_image")
    val facilitiesImage: String,

    var isSelected: Boolean = false
)
