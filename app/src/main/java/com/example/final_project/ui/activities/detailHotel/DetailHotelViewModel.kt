package com.example.final_project.ui.activities.detailHotel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.favoriteHotel.FavoriteHotelDataResponse
import com.example.final_project.models.favoriteHotel.UpdateFavHotelBody
import com.example.final_project.models.favoriteHotel.UpsertFavoriteHotelResponse
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.CreateUserFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.GetUserFavoriteHotelResponse
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.UpdateUserFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.UpsertUserFavoriteHotelResponse
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

    val _favHotelLiveData: MutableLiveData<Resource<FavoriteHotelDataResponse>> = MutableLiveData()
    val favFieldFilter = "*.*,hotels.hotels_hotel_id.*"

    val _favTourismID: MutableLiveData<Resource<Long>> = MutableLiveData()

    val _updateFavHotelLiveData: MutableLiveData<Resource<UpsertFavoriteHotelResponse>> = MutableLiveData()

    val _createUserFavHotelLiveData: MutableLiveData<Resource<UpsertUserFavoriteHotelResponse>> = MutableLiveData()
    val _updateUserFavHotelLiveData: MutableLiveData<Resource<UpsertUserFavoriteHotelResponse>> = MutableLiveData()
    val _getUserFavHotelLiveData: MutableLiveData<Resource<GetUserFavoriteHotelResponse>> = MutableLiveData()

    fun createUserFavoriteHotel(createUserFavoriteHotelBody: CreateUserFavoriteHotelBody) = viewModelScope.launch {
        _createUserFavHotelLiveData.postValue(Resource.Loading())
        val resp = repository.createUserFavoriteHotel(createUserFavoriteHotelBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _createUserFavHotelLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun updateUserFavoriteHotel(userFavHotelID: Long, updateUserFavoriteHotelBody: UpdateUserFavoriteHotelBody) = viewModelScope.launch {
        _updateUserFavHotelLiveData.postValue(Resource.Loading())
        val resp = repository.updateUserFavoriteHotel(userFavHotelID, updateUserFavoriteHotelBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _updateUserFavHotelLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getUserFavoriteHotel(hotelID: Long, userID: String) = viewModelScope.launch {
        _getUserFavHotelLiveData.postValue(Resource.Loading())
        val resp = repository.getUserFavoriteHotel(hotelID, userID)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _getUserFavHotelLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getDetailHotel(hotelID: Long) = viewModelScope.launch {
        _hotelLiveData.postValue(Resource.Loading())
        val resp = repository.getDetailHotel(hotelID, fieldFilter)

        if (resp.isSuccessful) {
            resp.body()?.let {
                _hotelLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getFavoriteHotel(userID: String) = viewModelScope.launch {
        _favHotelLiveData.postValue(Resource.Loading())
        val resp = repository.getFavoriteHotel(userID, favFieldFilter)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _favHotelLiveData.postValue(Resource.Success(it))
                _favTourismID.postValue(Resource.Success(it.data[0].favoriteID))
            }
        }
    }

    fun updateFavoriteHotel(favoriteID: Long, updateFavHotelBody: UpdateFavHotelBody) = viewModelScope.launch {
        _updateFavHotelLiveData.postValue(Resource.Loading())
        val resp = repository.updateFavoriteHotel( favoriteID, updateFavHotelBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _updateFavHotelLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getUserID(): String {
        val userID = repository.getUserID()
        return userID.toString()
    }


}