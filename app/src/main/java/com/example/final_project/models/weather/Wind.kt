package com.example.final_project.models.weather

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")
    val speed: Double,

    @SerializedName("deg")
    val deg: Long,

    @SerializedName("gust")
    val gust: Double
)
