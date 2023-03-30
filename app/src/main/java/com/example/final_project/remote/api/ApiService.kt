package com.example.final_project.remote.api

import com.example.final_project.models.*
import com.example.final_project.models.dto.ListTourismResponse
import com.example.final_project.models.dto.ProvinceResponse
import com.example.final_project.models.favoriteTourism.*
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.CreateUserFavoriteTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.GetUserFavoriteTourismResponse
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.UpdateUserFavoriteTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.UpsertUserFavoriteTourismResponse
import com.example.final_project.models.hotel.HotelDetailResponse
import com.example.final_project.models.hotel.HotelFacilitiesSelectionResponse
import com.example.final_project.models.hotel.HotelListResponse
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

    @GET("/items/hfacilities")
    suspend fun getHotelFacilities(): Response<HotelFacilitiesSelectionResponse>

    @GET("/items/hotels")
    suspend fun getListHotels(
        @Query("filter[hotel_city][_eq]") address: String,
        @Query("filter[cluster][_eq]") cluster: Long
    ): Response<HotelListResponse>

    @GET("/items/hotels/{hotel_id}")
    suspend fun getDetailHotel(
        @Path("hotel_id") hotelID: Long,
        @Query("fields") fieldFilter: String = "*.*,facilities.hfacilities_hfacilities_id.*"
    ): Response<HotelDetailResponse>

    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    suspend fun loginUser(
        @Body loginBody: LoginBody
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/items/favorite_tourism")
    suspend fun createFavoriteTourism(
        @Body createFavoriteTourismBody: CreateFavoriteTourismBody
    ): Response<UpsertFavoriteTourismResponse>

    @GET("/items/favorite_tourism")
    suspend fun getFavoriteTourism(
        @Query("filter[user_id][_eq]") user_id: String,
        @Query("fields") fields: String = "*.*,tourisms.tourisms_tourism_id.*"):Response<FavoriteTourismDataResponse>

    @PATCH("/items/favorite_tourism/{favorite_id}")
    suspend fun updateFavoriteTourism(
        @Path("favorite_id") favID: Long,
        @Body updateFavTourismBody: UpdateFavTourismBody
    ): Response<UpsertFavoriteTourismResponse>

    @Headers("Content-Type: application/json")
    @POST("items/user_favorite_tourism")
    suspend fun createUserFavoriteTourism(
        @Body createUserFavoriteTourismBody: CreateUserFavoriteTourismBody
    ): Response<UpsertUserFavoriteTourismResponse>

    @Headers("Content-Type: application/json")
    @PATCH("items/user_favorite_tourism/{tourism_id}")
    suspend fun updateUserFavoriteTourism(
        @Path("tourism_id") tourismID: Long,
        @Body updateUserFavoriteTourismBody: UpdateUserFavoriteTourismBody
    ): Response<UpsertUserFavoriteTourismResponse>

    @GET("items/user_favorite_tourism")
    suspend fun getUserFavoriteTourism(
        @Query("filter[tourism_id][_eq]") tourismID: Long,
        @Query("filter[user_id][_eq]") userID: String
    ): Response<GetUserFavoriteTourismResponse>
}
