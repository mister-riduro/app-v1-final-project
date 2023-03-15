package com.example.final_project.remote.api

import com.example.final_project.models.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("lang") lang: String
    ): Response<WeatherResponse>
}