package com.example.final_project.remote.api

import com.example.final_project.models.*
import com.example.final_project.models.dto.ListTourismResponse
import com.example.final_project.models.dto.ProvinceResponse
import com.example.final_project.models.login.LoginBody
import com.example.final_project.models.login.LoginResponse
import com.example.final_project.models.profiles.Profile
import com.example.final_project.models.profiles.ProfileBody
import com.example.final_project.models.profiles.ProfileLocation
import com.example.final_project.models.profiles.ProfileResponse
import com.example.final_project.models.tourism.DetailTourismResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/items/favorite_tourism")
    suspend fun getFavoriteTourism(
        @Header("Authorization") token_auth: String?,
        @Query("filter[user_id][_eq]") user_id: Long,
        @Query("fields") fields: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*"):Response<List<FavoriteTourism>>

    @GET("/users/{userid}")
    suspend fun getUserData(
        @Path("userid") userID: String
    ): Response<ProfileResponse>

    @GET("/items/tourisms/{tourism_id}")
    suspend fun getDetailTourism(
        @Path("tourism_id") tourism_id: Long,
        @Query("fields") fields: String = "*.*,routes.routes_id,facilities.tfacilities_tfacilities_id.*"): Response<DetailTourismResponse>

    @GET("/items/tourisms")
    suspend fun getTourismByType(
        @Query("fields") fields: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*",
        @Query("filter[tourism_type][_eq]") tourismType: String):
            Response<ListTourismResponse>

    @GET("/items/provinces")
    suspend fun getAllProvinces(): Response<ProvinceResponse>

    @GET("/items/tourisms")
    suspend fun getTourismByProvince(
        @Query("fields") fields: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*",
        @Query("filter[province_name][_eq]") tourismProvince: String
    ): Response<ListTourismResponse>

    @Headers("Content-Type: application/json")
    @POST("/users")
    suspend fun createUser(
       @Body profileBody: ProfileBody
    ): Response<ProfileResponse>

    @Headers("Content-Type: application/json")
    @PATCH("/users/{id}")
    suspend fun updateUserLocation(
        @Path("id") userID: String,
        @Body profileLocation: ProfileLocation
    ): Response<Profile>

    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    suspend fun loginUser(
        @Body loginBody: LoginBody
    ): Response<LoginResponse>

}