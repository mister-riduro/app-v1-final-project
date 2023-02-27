package com.example.final_project.ui.activities.areaBasedTourism

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository

class AreaBasedTourismViewModelFactory(
    val app: Application,
    private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AreaBasedTourismViewModel(app, repository) as T
    }
}