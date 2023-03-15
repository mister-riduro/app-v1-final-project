package com.example.final_project.ui.fragments.beranda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.fragments.profile.ProfilViewModel

class BerandaViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BerandaViewModel(repository) as T
    }
}