package com.example.final_project.ui.activities.typeBasedTourism

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.tourism.listTourism.ListTourismResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class TypeBasedTourismViewModel (
    private val repository: Repository,
    application: Application,
    private val tourismType: String
) : AndroidViewModel(application) {
    val _tourismLiveData: MutableLiveData<Resource<ListTourismResponse>> = MutableLiveData()
    val fieldFilter: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*"
    fun getTourismByType(tourismType: String) = viewModelScope.launch {
        _tourismLiveData.postValue(Resource.Loading())
        val resp = repository.getTourismByType(tourismType)
        _tourismLiveData.postValue(handleTourismByTypeResponse(resp))
    }

    private fun handleTourismByTypeResponse(response: Response<ListTourismResponse>): Resource<ListTourismResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}