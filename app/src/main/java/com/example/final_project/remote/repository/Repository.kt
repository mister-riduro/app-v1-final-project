package com.example.final_project.remote.repository

import com.example.final_project.remote.api.RetrofitInstance

class Repository (){
        suspend fun getDetailTourism(tourismID: Long, fieldFilter: String) = RetrofitInstance.api.getDetailTourism(tourismID, fieldFilter)
        suspend fun getTourismByType(fieldFilter:String, tourismType: String) = RetrofitInstance.api.getTourismByType(fieldFilter, tourismType)
        suspend fun getAllProvinces() = RetrofitInstance.api.getAllProvinces()
}