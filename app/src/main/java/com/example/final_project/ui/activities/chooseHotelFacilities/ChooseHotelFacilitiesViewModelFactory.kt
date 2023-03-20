package com.example.final_project.ui.activities.chooseHotelFacilities

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository

class ChooseHotelFacilitiesViewModelFactory(
    private val repository: Repository,
    val app: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChooseHotelFacilitiesViewModel(repository, app) as T
    }
}