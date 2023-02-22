package com.example.final_project.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.final_project.models.DetailTourism
import com.example.final_project.remote.api.RetrofitHelper
import com.example.final_project.remote.network.NetworkResult
import com.example.final_project.remote.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTourismViewModel @Inject constructor(
    private val repository: Repository,
    application: Application) : AndroidViewModel(application) {

    private val _tourismLiveData: MutableLiveData<NetworkResult<DetailTourism>> = MutableLiveData()
    val tourismLiveData: LiveData<NetworkResult<DetailTourism>> = _tourismLiveData

    fun fetchTourismResponse(tourismID: Long) = viewModelScope.launch {
        repository.getDetailTourism(tourismID).collect { values ->
            _tourismLiveData.value = values
        }
    }
}