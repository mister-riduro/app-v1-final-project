package com.example.final_project.ui.fragments.wisata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.dto.ProvinceResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WisataViewModel(
    private val repository: Repository
): ViewModel() {

    val _provinceLiveData: MutableLiveData<Resource<ProvinceResponse>> = MutableLiveData()

    fun getAllProvinces() = viewModelScope.launch {
        _provinceLiveData.postValue(Resource.Loading())
        val resp = repository.getAllProvinces()
        _provinceLiveData.postValue(handleProvinceResponse(resp))
    }

    private fun handleProvinceResponse(response: Response<ProvinceResponse>): Resource<ProvinceResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}