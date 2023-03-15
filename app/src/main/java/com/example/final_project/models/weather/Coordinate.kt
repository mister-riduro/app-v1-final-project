package com.example.final_project.models.weather

import com.google.gson.annotations.SerializedName

data class Coordinate(

    @SerializedName("lon")
    val lon: Double,

    @SerializedName("lat")
    val lat: Double
)
