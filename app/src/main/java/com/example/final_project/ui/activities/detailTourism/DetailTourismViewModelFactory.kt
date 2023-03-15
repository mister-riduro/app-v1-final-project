package com.example.final_project.ui.activities.detailTourism

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository

class DetailTourismViewModelFactory(
    private val repository: Repository,
    val app: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailTourismViewModel(repository, app) as T
    }
}