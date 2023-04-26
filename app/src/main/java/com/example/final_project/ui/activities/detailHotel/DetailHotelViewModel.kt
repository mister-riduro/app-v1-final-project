package com.example.final_project.ui.activities.detailHotel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.distance.DistanceDataResponse
import com.example.final_project.models.hotel.hotelDetail.HotelDetailResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class DetailHotelViewModel(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    val _hotelLiveData: MutableLiveData<Resource<HotelDetailResponse>> = MutableLiveData()
    val filterHotelFacilities = "*.*,facilities.newhfacilities_hfacilities_id.*"

    val _nearestDestLiveData: MutableLiveData<Resource<DistanceDataResponse>> = MutableLiveData()
    val filterNearestDestination = "*.*,tourism_id.tourism_id.*"

    fun getDetailHotel(hotelID: Long) = viewModelScope.launch {
        _hotelLiveData.postValue(Resource.Loading())
        val resp = repository.getDetailHotel(hotelID, filterHotelFacilities)

        Log.d("DETAIL HOTEL", "$resp")

        if (resp.isSuccessful) {
            resp.body()?.let {
                _hotelLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getNearestDestination(hotelID: Long) = viewModelScope.launch {
        _nearestDestLiveData.postValue(Resource.Loading())
        val resp = repository.getNearestDestination(filterNearestDestination, hotelID)

        if (resp.isSuccessful) {
            resp.body()?.let {
                _nearestDestLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getUserID(): String {
        val userID = repository.getUserID()
        return userID.toString()
    }
}