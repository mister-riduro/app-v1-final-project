package com.example.final_project.ui.activities.listHotel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.hotel.*
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class ListHotelViewModel(
    private val repository: Repository,
    val app: Application
): ViewModel() {
    val _clusterLiveData: MutableLiveData<Resource<ClusterResponse>> = MutableLiveData()
    val _hotelLiveData: MutableLiveData<Resource<HotelListResponse>> = MutableLiveData()

    fun getCluster(recommendationBody: RecommendationBody) = viewModelScope.launch {
        _clusterLiveData.postValue(Resource.Loading())
        val resp = repository.getHotelRecommendation(recommendationBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _clusterLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getHotel(city: String, cluster: Long) = viewModelScope.launch {
        _hotelLiveData.postValue(Resource.Loading())
        val resp = repository.getListHotels(city, cluster)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _hotelLiveData.postValue(Resource.Success(it))
            }
        }
    }

}