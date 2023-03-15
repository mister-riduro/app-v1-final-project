package com.example.final_project.ui.activities.detailTourism

import android.app.Application
import androidx.lifecycle.*
import com.example.final_project.models.tourism.DetailTourismResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class DetailTourismViewModel(
    private val repository: Repository,
    application: Application) : AndroidViewModel(application) {

    val _tourismLiveData: MutableLiveData<Resource<DetailTourismResponse>> = MutableLiveData()
    val fieldFilter: String = "*.*,routes.routes_id,facilities.tfacilities_tfacilities_id.*"
    fun getDetailTourism(tourismID: Long) = viewModelScope.launch {
        _tourismLiveData.postValue(Resource.Loading())
        val resp = repository.getDetailTourism(tourismID, fieldFilter)
        _tourismLiveData.postValue(handleDetailTourismResponse(resp))
    }

    private fun handleDetailTourismResponse(response: Response<DetailTourismResponse>): Resource<DetailTourismResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}