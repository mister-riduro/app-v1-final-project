package com.example.final_project.models.hotel

import com.google.gson.annotations.SerializedName

data class HotelDetailResponse(
    @SerializedName("data")
    val data: HotelDetail
)
