package com.example.final_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_project.databinding.ItemNearestEventBinding
import com.example.final_project.models.NearestEvent
import com.example.final_project.ui.activities.ActivityEventDetail

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

                holder.itemView.setOnClickListener {
                    val intent = Intent(it.context, ActivityEventDetail::class.java)

                    intent.putExtra(ActivityEventDetail.EXTRA_NAME, this.event_name)
                    intent.putExtra(ActivityEventDetail.EXTRA_DATE, this.event_date)
                    intent.putExtra(ActivityEventDetail.EXTRA_LOCATION, this.event_loc)
                    intent.putExtra(ActivityEventDetail.EXTRA_DESC, this.event_desc)
                    intent.putExtra(ActivityEventDetail.EXTRA_TYPE, this.event_type)
                    intent.putExtra(ActivityEventDetail.EXTRA_BG, this.background)

                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return nearest_event.size
    }
}