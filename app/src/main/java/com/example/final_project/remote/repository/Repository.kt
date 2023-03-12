package com.example.final_project.remote.repository

import com.example.final_project.models.login.LoginBody
import com.example.final_project.models.profiles.ProfileBody
import com.example.final_project.models.profiles.ProfileLocation
import com.example.final_project.remote.api.RetrofitInstance
import com.example.final_project.remote.api.RetrofitLocInstance

class Repository (){

        suspend fun getDetailTourism(tourismID: Long, fieldFilter: String) = RetrofitInstance.api.getDetailTourism(tourismID, fieldFilter)
        suspend fun getTourismByType(fieldFilter:String, tourismType: String) = RetrofitInstance.api.getTourismByType(fieldFilter, tourismType)
        suspend fun getAllProvinces() = RetrofitInstance.api.getAllProvinces()
        suspend fun getTourismByProvince(fieldFilter: String, tourismProvince: String) = RetrofitInstance.api.getTourismByProvince(fieldFilter, tourismProvince)

        suspend fun createUser(profileBody: ProfileBody) = RetrofitInstance.api.createUser(profileBody)
        suspend fun updateUserLocation(userID: String, profileLocation: ProfileLocation) = RetrofitInstance.api.updateUserLocation(userID, profileLocation)
        suspend fun getAllProvincesBynder(apiKey: String) = RetrofitLocInstance.apiLocation.getAllProvinces(apiKey)
        suspend fun getCitiesByProvinces(apiKey: String, provinceID: String) = RetrofitLocInstance.apiLocation.getCityByProvince(apiKey, provinceID)
        suspend fun loginAccount(loginBody: LoginBody) = RetrofitInstance.api.loginUser(loginBody)
}