package com.example.final_project.models.distance


import com.google.gson.annotations.SerializedName

data class DistanceDataResponse(
    @SerializedName("data")
    val `data`: List<DistanceData>
)