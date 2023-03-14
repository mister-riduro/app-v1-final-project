package com.example.final_project.ui.activities.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.login.LoginBody
import com.example.final_project.models.login.LoginResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    val _loginLiveData: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun loginAccount(loginBody: LoginBody) = viewModelScope.launch {
        _loginLiveData.postValue(Resource.Loading())

        val resp = repository.loginAccount(loginBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _loginLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun setValues(token: String, expiredTime: Int, isLoggedIn: Boolean) {
        repository.setToken(token)
        repository.setExpiredTime(expiredTime)
        repository.setLoggedIn(isLoggedIn)
    }

}