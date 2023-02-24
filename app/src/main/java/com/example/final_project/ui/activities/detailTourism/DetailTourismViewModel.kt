package com.example.final_project.ui.activities.detailTourism

import android.app.Application
import androidx.lifecycle.*
import com.example.final_project.models.DetailTourism
import com.example.final_project.models.dto.DetailTourismResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class DetailTourismViewModel @Inject constructor(
    private val repository: Repository,
    application: Application,
    tourismID: Long) : AndroidViewModel(application) {

    val _tourismLiveData: MutableLiveData<Resource<DetailTourism>> = MutableLiveData()
    val fieldFilter: String = "*.*,routes.routes_id_routes_id,facilities.tfacilities_tfacilities_id.*"
    fun getDetailTourism(tourismID: Long) = viewModelScope.launch {
        _tourismLiveData.postValue(Resource.Loading())
        val resp = repository.getDetailTourism(tourismID, fieldFilter)
        _tourismLiveData.postValue(handleDetailTourismResponse(resp))
    }

    private fun handleDetailTourismResponse(response: Response<DetailTourism>): Resource<DetailTourism> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}