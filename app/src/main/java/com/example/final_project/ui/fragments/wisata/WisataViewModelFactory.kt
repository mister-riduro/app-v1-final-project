package com.example.final_project.ui.fragments.wisata

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository

class WisataViewModelFactory(
    private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WisataViewModel(repository) as T
    }
}