package com.example.final_project.ui.activities.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.profiles.ProfileBody
import com.example.final_project.models.profiles.ProfileWrapper
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: Repository,
    application: Application,
):AndroidViewModel(application) {

    val _userLiveData: MutableLiveData<Resource<ProfileWrapper>> = MutableLiveData()

    fun createUser(profile: ProfileBody) = viewModelScope.launch {
        _userLiveData.postValue(Resource.Loading())

        val resp = repository.createUser(profile)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _userLiveData.postValue(Resource.Success(it))
            }
        }
    }
}