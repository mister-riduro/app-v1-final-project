package com.example.final_project.ui.activities.areaBasedTourism

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.dto.ListTourismResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class AreaBasedTourismViewModel(
    application: Application,
    private val repository: Repository,
):AndroidViewModel(application) {
    val _tourismLiveData: MutableLiveData<Resource<ListTourismResponse>> = MutableLiveData()
    val fieldFilter: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*"

    fun getTourismByProvince(tourismProvince: String) = viewModelScope.launch {
        _tourismLiveData.postValue(Resource.Loading())
        val resp = repository.getTourismByProvince(fieldFilter, tourismProvince)
        _tourismLiveData.postValue(handleTourismByProvinceResponse(resp))
    }

    private fun handleTourismByProvinceResponse(response: Response<ListTourismResponse>): Resource<ListTourismResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}