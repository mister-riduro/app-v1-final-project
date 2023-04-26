package com.example.final_project.ui.activities.detailTourism

import android.app.Application
import androidx.lifecycle.*
import com.example.final_project.models.favoriteTourism.FavoriteTourismDataResponse
import com.example.final_project.models.favoriteTourism.UpsertFavoriteTourismResponse
import com.example.final_project.models.favoriteTourism.UpdateFavTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.CreateUserFavoriteTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.GetUserFavoriteTourismResponse
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.UpdateUserFavoriteTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.UpsertUserFavoriteTourismResponse
import com.example.final_project.models.tourism.DetailTourismResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class DetailTourismViewModel(
    private val repository: Repository,
    application: Application) : AndroidViewModel(application) {

    val _tourismLiveData: MutableLiveData<Resource<DetailTourismResponse>> = MutableLiveData()
    val fieldFilter = "*.*,routes.routes_id,facilities.tfacilities_tfacilities_id.*"


    fun getDetailTourism(tourismID: Long) = viewModelScope.launch {
        _tourismLiveData.postValue(Resource.Loading())
        val resp = repository.getDetailTourism(tourismID, fieldFilter)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _tourismLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getUserID(): String {
        val userID = repository.getUserID()
        return userID.toString()
    }

    fun getToken(): String {
        val token = repository.getToken()
        return token.toString()
    }
}