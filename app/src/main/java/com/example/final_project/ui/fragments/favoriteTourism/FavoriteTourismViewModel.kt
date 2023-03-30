package com.example.final_project.ui.fragments.favoriteTourism

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.favoriteTourism.FavoriteTourismDataResponse
import com.example.final_project.models.profiles.ProfileResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class FavoriteTourismViewModel(private val repository: Repository): ViewModel() {
    val _tourismLiveData: MutableLiveData<Resource<FavoriteTourismDataResponse>> = MutableLiveData()
    val fieldFilter: String = "*.*,tourisms.tourisms_tourism_id.*"

    fun getFavoriteTourism(userID: String) = viewModelScope.launch {
        _tourismLiveData.postValue(Resource.Loading())
        val resp = repository.getFavoriteTourism(userID, fieldFilter)

        Log.d("FAV TOUR VM", "${resp.body()?.data}")

        if (resp.isSuccessful) {
            resp.body()?.let {
                _tourismLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getUserID(): String {
        return repository.getUserID().toString()
    }
}