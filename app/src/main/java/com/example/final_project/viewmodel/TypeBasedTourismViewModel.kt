package com.example.final_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.final_project.models.DetailTourism
import com.example.final_project.remote.network.NetworkResult
import com.example.final_project.remote.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypeBasedTourismViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {
    private val _tourismLiveData: MutableLiveData<NetworkResult<List<DetailTourism>>> = MutableLiveData()
    val tourismLiveData: LiveData<NetworkResult<List<DetailTourism>>> = _tourismLiveData

    fun fetchTourismResponse(tourismType: String) = viewModelScope.launch {
        repository.getTourismByType(tourismType).collect { values ->
            _tourismLiveData.value = values
        }
    }
}