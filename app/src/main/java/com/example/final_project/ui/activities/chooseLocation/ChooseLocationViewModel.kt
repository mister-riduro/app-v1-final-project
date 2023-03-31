package com.example.final_project.ui.activities.chooseLocation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.favoriteTourism.CreateFavoriteTourismBody
import com.example.final_project.models.profiles.Profile
import com.example.final_project.models.profiles.ProfileLocation
import com.example.final_project.models.bynderbyte.BynderCityResponse
import com.example.final_project.models.bynderbyte.BynderProvinceResponse
import com.example.final_project.models.favoriteHotel.CreateFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.UpsertFavoriteHotelResponse
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.UpdateUserFavoriteHotelBody
import com.example.final_project.models.favoriteTourism.UpsertFavoriteTourismResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ChooseLocationViewModel(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    val _userLocationLiveData: MutableLiveData<Resource<Profile>> = MutableLiveData()
    val _provinceLiveData: MutableLiveData<Resource<BynderProvinceResponse>> = MutableLiveData()
    val _cityLiveData: MutableLiveData<Resource<BynderCityResponse>> = MutableLiveData()
    val _userFavoriteTourismData : MutableLiveData<Resource<UpsertFavoriteTourismResponse>> = MutableLiveData()
    val _userFavoriteHotelData : MutableLiveData<Resource<UpsertFavoriteHotelResponse>> = MutableLiveData()

    val apiKey = "ffe27d799fb0769d7e1eec9e99127a0b539963b0682aa7c3f47f7c77e5d2b938"

    fun getProvinces() = viewModelScope.launch {
        _provinceLiveData.postValue(Resource.Loading())
        val resp = repository.getAllProvincesBynder(apiKey)
        _provinceLiveData.postValue(handleGetProvincesResponse(resp))
    }


    fun createUserFavoriteTourism(userFavoriteTourismBody: CreateFavoriteTourismBody) = viewModelScope.launch {
        _userFavoriteTourismData.postValue(Resource.Loading())

        val resp = repository.createFavoriteTourism(userFavoriteTourismBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _userFavoriteTourismData.postValue(Resource.Success(it))
            }
        }
    }

    fun createUserFavoriteHotel(userFavoriteHotelBody: CreateFavoriteHotelBody) = viewModelScope.launch {
        _userFavoriteHotelData.postValue(Resource.Loading())
        val resp = repository.createFavoriteHotel(userFavoriteHotelBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _userFavoriteHotelData.postValue(Resource.Success(it))
            }
        }
    }


    private fun handleGetProvincesResponse(response: Response<BynderProvinceResponse>): Resource<BynderProvinceResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

    fun getCities(provinceID: String) = viewModelScope.launch {
        _cityLiveData.postValue(Resource.Loading())
        val resp = repository.getCitiesByProvinces(apiKey, provinceID)
        _cityLiveData.postValue(handleGetCitiesResponse(resp))
    }

    private fun handleGetCitiesResponse(response: Response<BynderCityResponse>): Resource<BynderCityResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

    fun updateLocation(userID: String, profileLocation: ProfileLocation) = viewModelScope.launch {
        _userLocationLiveData.postValue(Resource.Loading())
        val resp = repository.updateUserLocation(userID, profileLocation)
        _userLocationLiveData.postValue(handleUpdateUserLocationResponse(resp))
    }

    private fun handleUpdateUserLocationResponse(response: Response<Profile>): Resource<Profile> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

}