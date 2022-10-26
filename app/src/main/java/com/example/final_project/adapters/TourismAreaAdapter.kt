package com.example.final_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_project.databinding.ItemNearestEventBinding
import com.example.final_project.databinding.ItemTourismAreaBinding
import com.example.final_project.models.NearestEvent
import com.example.final_project.models.TourismArea

class TourismAreaAdapter (private val area_tourism: ArrayList<TourismArea>): RecyclerView.Adapter<TourismAreaAdapter.TourismAreaViewHolder>() {
    inner class TourismAreaViewHolder(val binding: ItemTourismAreaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourismAreaViewHolder {
        val binding = ItemTourismAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourismAreaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TourismAreaViewHolder, position: Int) {
        with(holder) {
            with(area_tourism[position]) {
                Glide.with(itemView.context).load(this.background).into(binding.ivTourismArea)
                binding.tvArea.text = this.area
                binding.tvAmountTourism.text = this.amount.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return area_tourism.size
    }
}