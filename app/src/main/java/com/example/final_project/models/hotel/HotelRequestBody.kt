package com.example.final_project.models.hotel

data class HotelRequestBody(
    val facilities: ArrayList<String>,
    val city: String,
    val rating: Double
)
