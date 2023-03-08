package com.example.final_project.remote.repository

import com.example.final_project.models.ProfileBody
import com.example.final_project.remote.api.RetrofitInstance

class Repository (){
        suspend fun getDetailTourism(tourismID: Long, fieldFilter: String) = RetrofitInstance.api.getDetailTourism(tourismID, fieldFilter)
        suspend fun getTourismByType(fieldFilter:String, tourismType: String) = RetrofitInstance.api.getTourismByType(fieldFilter, tourismType)
        suspend fun getAllProvinces() = RetrofitInstance.api.getAllProvinces()
        suspend fun getTourismByProvince(fieldFilter: String, tourismProvince: String) = RetrofitInstance.api.getTourismByProvince(fieldFilter, tourismProvince)

        suspend fun createUser(profileBody: ProfileBody) = RetrofitInstance.api.createUser(profileBody)
}