package com.example.final_project.ui.fragments.favoriteHotel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.favoriteHotel.favoriteHotelNew.NewFavoriteHotelResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class FavoriteHotelViewModel(private val repository: Repository): ViewModel() {
    val _hotelLiveData: MutableLiveData<Resource<NewFavoriteHotelResponse>> = MutableLiveData()
    val fieldFilter: String = "*.*,hotels.newhotels_hotel_id.*"

//    fun getFavoriteHotel(userID: String) = viewModelScope.launch {
//        _hotelLiveData.postValue(Resource.Loading())
//        val resp = repository.getFavoriteHotel(userID, fieldFilter)
//
//        Log.d("FAV TOUR VM", "${resp.body()?.data}")
//
//        if (resp.isSuccessful) {
//            resp.body()?.let {
//                _hotelLiveData.postValue(Resource.Success(it))
//            }
//        }
//    }

    fun getUserID(): String {
        return repository.getUserID().toString()
    }

}