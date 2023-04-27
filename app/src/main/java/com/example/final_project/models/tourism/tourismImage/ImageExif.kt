package com.example.final_project.models.tourism.tourismImage


import com.google.gson.annotations.SerializedName

data class ImageExif(
    @SerializedName("ExposureTime")
    val exposureTime: Double,
    @SerializedName("FNumber")
    val fNumber: Any,
    @SerializedName("FocalLength")
    val focalLength: Any,
    @SerializedName("ISO")
    val iSO: Int
)