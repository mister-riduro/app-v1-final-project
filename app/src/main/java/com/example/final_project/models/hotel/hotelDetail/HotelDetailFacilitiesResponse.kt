package com.example.final_project.models.hotel.hotelDetail

import com.google.gson.annotations.SerializedName

data class HotelDetailFacilitiesResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("newhotels_hotel_id")
    val newHotelsHotelID: Long,

    @SerializedName("newhfacilities_hfacilities_id")
    val newHfacilitiesHfacilitiesID: HotelDetailFacilities
)
