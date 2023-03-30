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

    val _favTourismLiveData: MutableLiveData<Resource<FavoriteTourismDataResponse>> = MutableLiveData()
    val favFieldFilter = "*.*,tourisms.tourisms_tourism_id.*"

    val _favTourismID: MutableLiveData<Resource<Long>> = MutableLiveData()

    val _updateFavTourismLiveData: MutableLiveData<Resource<UpsertFavoriteTourismResponse>> = MutableLiveData()

    val _createUserFavTourismLiveData: MutableLiveData<Resource<UpsertUserFavoriteTourismResponse>> = MutableLiveData()
    val _updateUserFavTourismLiveData: MutableLiveData<Resource<UpsertUserFavoriteTourismResponse>> = MutableLiveData()
    val _getUserFavTourismLiveData: MutableLiveData<Resource<GetUserFavoriteTourismResponse>> = MutableLiveData()


    fun createUserFavoriteTourism(createUserFavoriteTourismBody: CreateUserFavoriteTourismBody) = viewModelScope.launch {
        _createUserFavTourismLiveData.postValue(Resource.Loading())
        val resp = repository.createUserFavoriteTourism(createUserFavoriteTourismBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _createUserFavTourismLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun updateUserFavoriteTourism(userFavTourismID: Long, updateUserFavoriteTourismBody: UpdateUserFavoriteTourismBody) = viewModelScope.launch {
        _updateUserFavTourismLiveData.postValue(Resource.Loading())
        val resp = repository.updateUserFavoriteTourism(userFavTourismID, updateUserFavoriteTourismBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _updateUserFavTourismLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getUserFavoriteTourism(tourismID: Long, userID: String) = viewModelScope.launch {
        _getUserFavTourismLiveData.postValue(Resource.Loading())
        val resp = repository.getUserFavoriteTourism(tourismID, userID)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _getUserFavTourismLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getDetailTourism(tourismID: Long) = viewModelScope.launch {
        _tourismLiveData.postValue(Resource.Loading())
        val resp = repository.getDetailTourism(tourismID, fieldFilter)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _tourismLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getFavoriteTourism(userID: String) = viewModelScope.launch {
        _favTourismLiveData.postValue(Resource.Loading())
        val resp = repository.getFavoriteTourism(userID, favFieldFilter)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _favTourismLiveData.postValue(Resource.Success(it))
                _favTourismID.postValue(Resource.Success(it.data[0].favoriteID))
            }
        }
    }

    fun updateFavoriteTourism(favoriteID: Long, updateFavTourismBody: UpdateFavTourismBody) = viewModelScope.launch {
        _updateFavTourismLiveData.postValue(Resource.Loading())
        val resp = repository.updateFavoriteTourism( favoriteID, updateFavTourismBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _updateFavTourismLiveData.postValue(Resource.Success(it))
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