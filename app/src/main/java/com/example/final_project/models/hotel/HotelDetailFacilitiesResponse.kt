package com.example.final_project.models.hotel

import com.google.gson.annotations.SerializedName

data class HotelDetailFacilitiesResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("newhotels_hotel_id")
    val newHotelsHotelID: Long,

    @SerializedName("newhfacilities_hfacilitiesid")
    val newHfacilitiesHfacilitiesID: HotelDetailFacilities
)
