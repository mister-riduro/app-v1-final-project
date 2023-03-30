package com.example.final_project.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.databinding.ItemAreaBasedTourismBinding
import com.example.final_project.databinding.ItemHotelFacilitiesBinding
import com.example.final_project.databinding.ItemTypeBasedTourismBinding
import com.example.final_project.models.hotel.HotelDetailFacilitiesResponse
import com.example.final_project.models.tourism.DetailTourism
import com.squareup.picasso.Picasso
import java.net.URL


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
            Picasso.get().load(facilities.hfacilitiesHfacilitiesID.facilitiesImage).into(binding.ivIconFacilities)
            binding.tvFacilitiesHotel.text = facilities.hfacilitiesHfacilitiesID.facilitiesName
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}