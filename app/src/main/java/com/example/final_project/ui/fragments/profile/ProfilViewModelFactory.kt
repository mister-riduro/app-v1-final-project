package com.example.final_project.ui.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository

class ProfilViewModelFactory(
    private val repository: Repository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfilViewModel(repository) as T
    }
}