package com.example.final_project.models.weather

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("type")
    val type: Long,

    @SerializedName("id")
    val id: Long,

    @SerializedName("country")
    val country: String,

    @SerializedName("sun_rise")
    val sunRise: Long,

    @SerializedName("sun_set")
    val sunSet: Long
)
