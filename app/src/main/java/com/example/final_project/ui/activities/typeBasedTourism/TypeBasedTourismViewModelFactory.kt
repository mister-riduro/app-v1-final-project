package com.example.final_project.ui.activities.typeBasedTourism

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository

class TypeBasedTourismViewModelFactory(
    private val repository: Repository,
    val app: Application,
    val tourismType: String): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TypeBasedTourismViewModel(repository, app, tourismType) as T
    }
}