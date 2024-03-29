package com.example.final_project.remote.repository

import com.example.final_project.models.favoriteHotel.CreateFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.UpdateFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.CreateUserFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.UpdateUserFavoriteHotelBody
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

        suspend fun getDetailTourism(tourismID: Int, fieldFilter: String) = RetrofitInstance.api.getDetailTourism(tourismID, fieldFilter)
        suspend fun getTourismByType(tourismType: String) = RetrofitInstance.api.getTourismByType(tourismType)
        suspend fun getAllProvinces() = RetrofitInstance.api.getAllProvinces()
        suspend fun getTourismByProvince(tourismProvince: String) = RetrofitInstance.api.getTourismByProvince(tourismProvince)
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
        suspend fun getNearestDestination(fieldFilter: String, hotelID: Long) = RetrofitInstance.api.getNearestDestinationData(fieldFilter, hotelID)

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