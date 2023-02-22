package com.example.final_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemRoutesBinding
import com.example.final_project.models.Routes

class RoutesListAdapter(private val routes: ArrayList<Routes>): RecyclerView.Adapter<RoutesListAdapter.RoutesListViewHolder>() {
    inner class RoutesListViewHolder(val binding: ItemRoutesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesListViewHolder {
        val binding = ItemRoutesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  RoutesListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  routes.size
    }

    override fun onBindViewHolder(holder: RoutesListViewHolder, position: Int) {
        with(holder) {
            with(routes[position]) {
                binding.tvRoutes.text = this.routesDesc
            }
        }
    }
}