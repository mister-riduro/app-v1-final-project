package com.example.final_project.ui.activities.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.profiles.ProfileBody
import com.example.final_project.models.profiles.ProfileResponse
import com.example.final_project.remote.preferences.Preferences
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch
import java.util.Calendar

class RegisterViewModel(
    private val repository: Repository,
    application: Application,
):AndroidViewModel(application) {

    val _userLiveData: MutableLiveData<Resource<ProfileResponse>> = MutableLiveData()

    fun createUser(profile: ProfileBody) = viewModelScope.launch {
        _userLiveData.postValue(Resource.Loading())

        val resp = repository.createUser(profile)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _userLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }
    fun isExpired(): Boolean {
        val cal = Calendar.getInstance()
        return  cal.timeInMillis >= repository.getExpiredTime()
    }

    fun resetPref() {
        Preferences.instance.setExpirationTime(0)
        Preferences.instance.setToken("")
        Preferences.instance.setUserID("")
        Preferences.instance.isLoggedIn(false)
    }
}