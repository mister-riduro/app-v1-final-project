package com.example.final_project.ui.activities.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.Profile
import com.example.final_project.models.ProfileBody
import com.example.final_project.models.ProfileWrapper
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(
    private val repository: Repository,
    application: Application,
):AndroidViewModel(application) {

    val _userLiveData: MutableLiveData<Resource<ProfileWrapper>> = MutableLiveData()

    fun createUser(profile: ProfileBody) = viewModelScope.launch {
        _userLiveData.postValue(Resource.Loading())

        val resp = repository.createUser(profile)
        Log.d("RESP BODY", "${resp.body()}")
        if (resp.isSuccessful) {
            resp.body()?.let {
                _userLiveData.postValue(Resource.Success(it))
            }
        }
    }

    private fun handleUserRegistrationResponse(response: Response<Profile>): Resource<Profile> {
        Log.d("HANDLE REG !SUCCESS", "${response}")
        if (response.isSuccessful) {
            Log.d("HANDLE USER REG", "${response}")
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}