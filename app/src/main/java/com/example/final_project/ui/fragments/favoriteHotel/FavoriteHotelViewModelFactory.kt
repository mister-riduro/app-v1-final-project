package com.example.final_project.ui.fragments.favoriteHotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.fragments.favoriteTourism.FavoriteTourismViewModel

class FavoriteHotelViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteHotelViewModel(repository) as T
    }
}