package com.example.final_project.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemHotelFacilitiesBinding
import com.example.final_project.models.hotel.hotelDetail.HotelDetailFacilitiesResponse
import com.squareup.picasso.Picasso


class HotelFacilitiesAdapter: RecyclerView.Adapter<HotelFacilitiesAdapter.HotelFacilitiesViewHolder>() {

    inner class HotelFacilitiesViewHolder(val binding: ItemHotelFacilitiesBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemHotelFacilitiesBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelFacilitiesViewHolder {
        binding = ItemHotelFacilitiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelFacilitiesViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<HotelDetailFacilitiesResponse>() {
        override fun areItemsTheSame(oldItem: HotelDetailFacilitiesResponse, newItem: HotelDetailFacilitiesResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HotelDetailFacilitiesResponse, newItem: HotelDetailFacilitiesResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(
        holder: HotelFacilitiesViewHolder,
        position: Int
    ) {
        val facilities = differ.currentList[position]

        holder.itemView.apply {
            try {
                Picasso.get().load(facilities.newHfacilitiesHfacilitiesID.facilitiesImage).into(binding.ivIconFacilities)
            } catch (e: Exception) {
                Log.d("LOAD IMAGE ERROR", "No Image Data")
            }
            binding.tvFacilitiesHotel.text = facilities.newHfacilitiesHfacilitiesID.facilitiesName
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}