package com.example.final_project.ui.activities.detailHotel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.hotel.HotelDetailResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class DetailHotelViewModel(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    val _hotelLiveData: MutableLiveData<Resource<HotelDetailResponse>> = MutableLiveData()
    val fieldFilter = "*.*,facilities.hfacilities_hfacilities_id.*"

    fun getDetailHotel(hotelID: Long) = viewModelScope.launch {
        _hotelLiveData.postValue(Resource.Loading())
        val resp = repository.getDetailHotel(hotelID, fieldFilter)

        if (resp.isSuccessful) {
            resp.body()?.let {
                _hotelLiveData.postValue(Resource.Success(it))
            }
        }
    }


}