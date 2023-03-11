package com.example.final_project.remote.api

import com.example.final_project.models.bynderbyte.BynderCityResponse
import com.example.final_project.models.bynderbyte.BynderProvinceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("provinsi")
    suspend fun getAllProvinces(
        @Query("api_key") apiKey: String = "8e49f28e0f2f2cf56393c352613eec358e85fb7077ce6f7f453ebb826a7b1f6d"
    ): Response<BynderProvinceResponse>

    @GET("kabupaten")
    suspend fun getCityByProvince(
        @Query("api_key") apiKey: String = "8e49f28e0f2f2cf56393c352613eec358e85fb7077ce6f7f453ebb826a7b1f6d",
        @Query("id_provinsi") provinceID: String
    ): Response<BynderCityResponse>
}