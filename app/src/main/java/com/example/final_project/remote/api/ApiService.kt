package com.example.final_project.remote.api

import com.example.final_project.models.DetailTourism
import com.example.final_project.models.FavoriteTourism
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/items/favorite_tourism?filter[user_id][_eq]={user_id}&fields=*,tourisms.tourisms_tourism_id.*")
    suspend fun getFavoriteTourism(
        @Path("user_id") user_id: String):Response<List<FavoriteTourism>>

    @GET("/items/tourisms/{tourism_id}?fields=*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*")
    suspend fun getDetailTourism(
        @Path("tourism_id") tourism_id: Long): Response<DetailTourism>

    @GET("/items/tourisms?fields=*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*&filter[tourism_type][_eq]={tourism_type}")
    suspend fun getTourismByType(
        @Path("tourism_type") tourism_type: String): Response<List<DetailTourism>>
}