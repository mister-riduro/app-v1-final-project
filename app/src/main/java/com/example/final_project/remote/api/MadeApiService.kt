package com.example.final_project.remote.api

import com.example.final_project.models.hotel.ClusterResponse
import com.example.final_project.models.hotel.RecommendationBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MadeApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/recommend")
    suspend fun getHotelRecommendation(
        @Body recommendationBody: RecommendationBody
    ): Response<ClusterResponse>
}