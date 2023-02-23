package com.example.final_project.remote.api

import com.example.final_project.models.DetailTourism
import com.example.final_project.models.FavoriteTourism
import com.example.final_project.models.dto.DetailTourismResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/items/favorite_tourism?filter[user_id][_eq]={user_id}&fields=*,tourisms.tourisms_tourism_id.*")
    suspend fun getFavoriteTourism(
        @Path("user_id") user_id: String):Response<List<FavoriteTourism>>

    @GET("/items/tourisms/{tourism_id}?fields=*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*")
    suspend fun getDetailTourism(
        @Path("tourism_id") tourism_id: Long): Response<DetailTourism>

    @GET("/items/tourisms")
    suspend fun getTourismByType(
        @Query("fields") fields: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*",
        @Query("filter[tourism_type][_eq]") tourismType: String):
            Response<DetailTourismResponse>
}