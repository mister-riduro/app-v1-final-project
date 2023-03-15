package com.example.final_project.models.weather

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val oneH: Double
)
