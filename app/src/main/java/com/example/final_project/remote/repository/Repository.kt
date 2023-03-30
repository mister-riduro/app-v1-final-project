package com.example.final_project.remote.repository

import com.example.final_project.models.favoriteTourism.CreateFavoriteTourismBody
import com.example.final_project.models.favoriteTourism.UpdateFavTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.CreateUserFavoriteTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.UpdateUserFavoriteTourismBody
import com.example.final_project.models.hotel.RecommendationBody
import com.example.final_project.models.login.LoginBody
import com.example.final_project.models.profiles.ProfileBody
import com.example.final_project.models.profiles.ProfileLocation
import com.example.final_project.remote.api.RetrofitInstance
import com.example.final_project.remote.api.RetrofitLocInstance
import com.example.final_project.remote.api.RetrofitMadeInstance
import com.example.final_project.remote.api.RetrofitWeatherInstance
import com.example.final_project.remote.preferences.Preferences

class Repository (){

        suspend fun getDetailTourism(tourismID: Long, fieldFilter: String) = RetrofitInstance.api.getDetailTourism(tourismID, fieldFilter)
        suspend fun getTourismByType(fieldFilter:String, tourismType: String) = RetrofitInstance.api.getTourismByType(fieldFilter, tourismType)
        suspend fun getAllProvinces() = RetrofitInstance.api.getAllProvinces()
        suspend fun getTourismByProvince(fieldFilter: String, tourismProvince: String) = RetrofitInstance.api.getTourismByProvince(fieldFilter, tourismProvince)
        suspend fun getUserData(userID: String) = RetrofitInstance.api.getUserData(userID)
        suspend fun createUser(profileBody: ProfileBody) = RetrofitInstance.api.createUser(profileBody)
        suspend fun updateUserLocation(userID: String, profileLocation: ProfileLocation) = RetrofitInstance.api.updateUserLocation(userID, profileLocation)
        suspend fun getAllProvincesBynder(apiKey: String) = RetrofitLocInstance.apiLocation.getAllProvinces(apiKey)
        suspend fun getCitiesByProvinces(apiKey: String, provinceID: String) = RetrofitLocInstance.apiLocation.getCityByProvince(apiKey, provinceID)
        suspend fun loginAccount(loginBody: LoginBody) = RetrofitInstance.api.loginUser(loginBody)

        suspend fun getWeatherData(city: String, apiKey: String, language: String) = RetrofitWeatherInstance.apiWeather.getWeatherData(city, apiKey, language)

        suspend fun getHotelRecommendation(recommendationBody: RecommendationBody) = RetrofitMadeInstance.apiMade.getHotelRecommendation(recommendationBody)

        suspend fun getHotelFacilities() = RetrofitInstance.api.getHotelFacilities()
        suspend fun getListHotels(city: String, cluster: Long) = RetrofitInstance.api.getListHotels(city, cluster)
        suspend fun getDetailHotel(hotelID: Long, fieldFilter: String) = RetrofitInstance.api.getDetailHotel(hotelID, fieldFilter)

        suspend fun getFavoriteTourism(userID: String, fieldFilter: String) = RetrofitInstance.api.getFavoriteTourism(userID, fieldFilter)
        suspend fun createFavoriteTourism(createFavoriteTourismBody: CreateFavoriteTourismBody) = RetrofitInstance.api.createFavoriteTourism(createFavoriteTourismBody)
        suspend fun updateFavoriteTourism(favoriteID: Long, updateFavTourismBody: UpdateFavTourismBody) = RetrofitInstance.api.updateFavoriteTourism(favoriteID, updateFavTourismBody)

        suspend fun createUserFavoriteTourism(createUserFavoriteTourismBody: CreateUserFavoriteTourismBody) = RetrofitInstance.api.createUserFavoriteTourism(createUserFavoriteTourismBody)
        suspend fun updateUserFavoriteTourism(userFavoriteTourismID: Long, updateUserFavoriteTourismBody: UpdateUserFavoriteTourismBody) = RetrofitInstance.api.updateUserFavoriteTourism(userFavoriteTourismID, updateUserFavoriteTourismBody)
        suspend fun getUserFavoriteTourism(tourismID: Long, userID: String) = RetrofitInstance.api.getUserFavoriteTourism(tourismID, userID)

        // Preferences
        fun setToken(value: String) = Preferences.instance.setToken(value)
        fun setExpiredTime(value: Int) = Preferences.instance.setExpirationTime(value)
        fun setLoggedIn(value: Boolean) = Preferences.instance.isLoggedIn(value)

        fun setUserID(value: String) = Preferences.instance.setUserID(value)
        fun getToken() = Preferences.instance.token
        fun getExpiredTime() = Preferences.instance.expiredTime
        fun isLoggedIn() = Preferences.instance.loggedIn

        fun getUserID() = Preferences.instance.userID
}