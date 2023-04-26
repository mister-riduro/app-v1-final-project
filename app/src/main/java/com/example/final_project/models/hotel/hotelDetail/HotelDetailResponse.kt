package com.example.final_project.models.hotel.hotelDetail

import com.example.final_project.models.hotel.hotelDetail.HotelDetail
import com.google.gson.annotations.SerializedName

data class HotelDetailResponse(
    @SerializedName("data")
    val data: HotelDetail
)
