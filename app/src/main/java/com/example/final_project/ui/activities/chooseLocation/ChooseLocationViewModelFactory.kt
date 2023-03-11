package com.example.final_project.ui.activities.chooseLocation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.register.RegisterViewModel

class ChooseLocationViewModelFactory(
    private val repository: Repository,
    val app: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChooseLocationViewModel(repository, app) as T
    }

}