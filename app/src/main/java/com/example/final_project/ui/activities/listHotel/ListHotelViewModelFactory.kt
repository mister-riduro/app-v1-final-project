package com.example.final_project.ui.activities.listHotel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.models.hotel.RecommendationBody
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.detailTourism.DetailTourismViewModel

class ListHotelViewModelFactory(
    private val repository: Repository,
    val app: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListHotelViewModel(repository, app) as T
    }

}