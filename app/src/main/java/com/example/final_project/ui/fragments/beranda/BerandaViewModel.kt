package com.example.final_project.ui.fragments.beranda

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.profiles.ProfileResponse
import com.example.final_project.models.weather.WeatherResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class BerandaViewModel(private val repository: Repository): ViewModel() {
    val _userLiveData: MutableLiveData<Resource<ProfileResponse>> = MutableLiveData()
    val _weatherLiveData: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()

    fun getUserData(userID: String) = viewModelScope.launch {
        _userLiveData.postValue(Resource.Loading())
        val resp = repository.getUserData(userID)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _userLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getWeatherData(city: String, apiKey: String, language: String) = viewModelScope.launch {
        var formattedCity = ""

        Log.d("NORMAL CITY", "$city")

        if (city.contains("KAB.")) {
            formattedCity = city.replace("\\s*KAB.\\s*".toRegex(), "")
        } else if (city.contains("KOTA")) {
            formattedCity = city.replace("\\s*KOTA\\s*".toRegex(), "")
        }

        formattedCity = formattedCity.lowercase()
        formattedCity = formattedCity.capitalize()

        Log.d("FORMATTED CITY", "$formattedCity")

        _weatherLiveData.postValue(Resource.Loading())
        val resp = repository.getWeatherData(formattedCity, apiKey, language)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _weatherLiveData.postValue(Resource.Success(it))
            }
        }
    }
}