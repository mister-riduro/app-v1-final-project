package com.example.final_project.ui.activities.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository

class RegisterViewModelFactory(
    private val repository: Repository,
    val app: Application,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(repository, app) as T
    }
}