package com.example.final_project.ui.activities.chooseHotelFacilities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.hotel.hotelFacilitiesSelection.HotelFacilitiesSelectionResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class ChooseHotelFacilitiesViewModel(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    val _hotelFacilitiesLiveData: MutableLiveData<Resource<HotelFacilitiesSelectionResponse>> = MutableLiveData()

    fun getHotelFacilities() = viewModelScope.launch {
        _hotelFacilitiesLiveData.postValue(Resource.Loading())
        val resp = repository.getHotelFacilities()
        if (resp.isSuccessful) {
            resp.body()?.let {
                _hotelFacilitiesLiveData.postValue(Resource.Success(it))
            }
        }
    }

}