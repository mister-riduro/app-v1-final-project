@file:Suppress("DEPRECATION")

package com.example.final_project.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.final_project.ui.fragments.FavoriteHotelFragment
import com.example.final_project.ui.fragments.favoriteTourism.FavoriteTourismFragment

private const val NUM_TABS = 2

class FavoriteTabAdapter(fragmentManager: FragmentManager, lifeCycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount(): Int {
        return  NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteTourismFragment()
            else -> {
                FavoriteHotelFragment()
            }
        }
    }


}