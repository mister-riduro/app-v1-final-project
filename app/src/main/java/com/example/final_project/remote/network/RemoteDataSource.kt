package com.example.final_project.remote.network

import com.example.final_project.remote.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getDetailTourism(tourismID: Long) = apiService.getDetailTourism(tourismID)
    suspend fun getAllTourismByType(tourismType: String) = apiService.getTourismByType(tourismType)
}