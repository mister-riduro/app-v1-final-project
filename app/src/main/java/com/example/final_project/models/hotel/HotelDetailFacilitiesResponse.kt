package com.example.final_project.models.hotel

import com.google.gson.annotations.SerializedName

data class HotelDetailFacilitiesResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("hotel_hotel_id")
    val hotelHotelID: Long,

    @SerializedName("hfacilities_hfacilities_id")
    val hfacilitiesHfacilitiesID: HotelDetailFacilities
)
