package com.example.final_project.ui.fragments.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.profiles.ProfileResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class ProfilViewModel(
    private val repository: Repository
):ViewModel() {
    val _userLiveData: MutableLiveData<Resource<ProfileResponse>> = MutableLiveData()

    fun getUserData(userID: String) = viewModelScope.launch {
        _userLiveData.postValue(Resource.Loading())
        val resp = repository.getUserData(userID)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _userLiveData.postValue(Resource.Success(it))
            }
        }
    }
}