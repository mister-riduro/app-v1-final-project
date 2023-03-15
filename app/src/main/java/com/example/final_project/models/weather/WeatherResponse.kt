package com.example.final_project.models.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coord: Coordinate,

    @SerializedName("weather")
    val weather: List<Weather>,

    @SerializedName("base")
    val base: String,

    @SerializedName("main")
    val main: Main,

    @SerializedName("visibility")
    val visibility: Long,

    @SerializedName("wind")
    val wind: Wind,

    @SerializedName("rain")
    val rain: Rain,

    @SerializedName("clouds")
    val clouds: Clouds,

    @SerializedName("dt")
    val dt: Long,

    @SerializedName("sys")
    val sys: Sys,

    @SerializedName("timezone")
    val timezone: Long,

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("code")
    val code: Long
)
