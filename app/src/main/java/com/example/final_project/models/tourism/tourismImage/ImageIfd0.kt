package com.example.final_project.models.tourism.tourismImage


import com.google.gson.annotations.SerializedName

data class ImageIfd0(
    @SerializedName("Make")
    val make: String,
    @SerializedName("Model")
    val model: String
)