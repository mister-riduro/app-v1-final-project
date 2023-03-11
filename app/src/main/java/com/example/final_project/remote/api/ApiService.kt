package com.example.final_project.remote.api

import com.example.final_project.models.*
import com.example.final_project.models.dto.DetailTourismResponse
import com.example.final_project.models.dto.ProvinceResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/items/favorite_tourism")
    suspend fun getFavoriteTourism(
        @Query("filter[user_id][_eq]") user_id: Long,
        @Query("fields") fields: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*"):Response<List<FavoriteTourism>>

    @GET("/items/tourisms/{tourism_id}")
    suspend fun getDetailTourism(
        @Path("tourism_id") tourism_id: Long,
        @Query("fields") fields: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*"): Response<DetailTourism>

    @GET("/items/tourisms")
    suspend fun getTourismByType(
        @Query("fields") fields: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*",
        @Query("filter[tourism_type][_eq]") tourismType: String):
            Response<DetailTourismResponse>

    @GET("/items/provinces")
    suspend fun getAllProvinces(): Response<ProvinceResponse>

    @GET("/items/tourisms")
    suspend fun getTourismByProvince(
        @Query("fields") fields: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*",
        @Query("filter[province_name][_eq]") tourismProvince: String
    ): Response<DetailTourismResponse>

    @Headers("Content-Type: application/json")
    @POST("/users")
    suspend fun createUser(
       @Body profileBody: ProfileBody
    ): Response<ProfileWrapper>


    @Headers("Content-Type: application/json")
    @PATCH("/users/{id}")
    suspend fun updateUserLocation(
        @Path("id") userID: String,
        @Body profileLocation: ProfileLocation
    ): Response<Profile>

}