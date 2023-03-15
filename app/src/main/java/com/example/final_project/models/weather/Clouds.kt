package com.example.final_project.models.weather

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Long
)
