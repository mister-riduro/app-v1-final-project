package com.example.final_project.models.tourism.tourismImage


import com.google.gson.annotations.SerializedName

data class ImageDataResponse(
    @SerializedName("data")
    val `data`: List<ImageData>
)