package com.example.final_project.ui.fragments.favoriteTourism

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository

class FavoriteTourismViewModelFactory ( private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteTourismViewModel(repository) as T
    }
}