package com.example.final_project.remote.api

import com.example.final_project.models.*
import com.example.final_project.models.distance.DistanceDataResponse
import com.example.final_project.models.dto.ListTourismResponse
import com.example.final_project.models.dto.ProvinceResponse
import com.example.final_project.models.favoriteHotel.CreateFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.UpdateFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.UpsertFavoriteHotelResponse
import com.example.final_project.models.favoriteHotel.favoriteHotelNew.NewFavoriteHotelResponse
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.CreateUserFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.GetUserFavoriteHotelResponse
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.UpdateUserFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.UpsertUserFavoriteHotelResponse
import com.example.final_project.models.favoriteTourism.*
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.CreateUserFavoriteTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.GetUserFavoriteTourismResponse
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.UpdateUserFavoriteTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.UpsertUserFavoriteTourismResponse
import com.example.final_project.models.hotel.hotelDetail.HotelDetailResponse
import com.example.final_project.models.hotel.hotelFacilitiesSelection.HotelFacilitiesSelectionResponse
import com.example.final_project.models.hotel.hotelList.HotelListResponse
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

    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    suspend fun loginUser(
        @Body loginBody: LoginBody
    ): Response<LoginResponse>

    // Hotel
    @GET("/items/newhfacilities")
    suspend fun getHotelFacilities(): Response<HotelFacilitiesSelectionResponse>

    @GET("/items/newhotels")
    suspend fun getListHotels(
        @Query("filter[hotel_city][_contains]") address: String,
        @Query("filter[cluster][_eq]") cluster: Long
    ): Response<HotelListResponse>

    @GET("/items/newhotels/{hotel_id}")
    suspend fun getDetailHotel(
        @Path("hotel_id") hotelID: Long,
        @Query("fields") fieldFilter: String = "*.*,facilities.newhfacilities_hfacilities_id.*"
    ): Response<HotelDetailResponse>

    @GET("/items/distance")
    suspend fun getNearestDestinationData(
        @Query("fields") fieldFilter: String = "*.*,tourism_id.tourism_id.*",
        @Query("filter[hotel_id][_eq]") hotelID: Long
    ): Response<DistanceDataResponse>
}
