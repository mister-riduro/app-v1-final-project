package com.example.final_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_project.databinding.ItemNearestEventBinding
import com.example.final_project.models.NearestEvent

class NearestEventAdapter(private val nearest_event: ArrayList<NearestEvent>): RecyclerView.Adapter<NearestEventAdapter.NearesEventViewHolder>() {
    inner class NearesEventViewHolder(val binding: ItemNearestEventBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearesEventViewHolder {
        val binding = ItemNearestEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NearesEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NearesEventViewHolder, position: Int) {
        with(holder) {
            with(nearest_event[position]) {
                Glide.with(itemView.context).load(this.background).into(binding.eventBackground)
                binding.tvEventName.text = this.event_name
                binding.tvEventDate.text = this.event_date
            }
        }
    }

    override fun getItemCount(): Int {
        return nearest_event.size
    }
}