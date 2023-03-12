package com.example.final_project.ui.activities.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.register.RegisterViewModel

class LoginViewModelFactory(
    private val repository: Repository,
    val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository, app) as T
    }
}