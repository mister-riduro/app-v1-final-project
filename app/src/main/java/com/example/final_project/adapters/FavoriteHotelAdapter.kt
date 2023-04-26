package com.example.final_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemFavoriteHotelBinding
import com.example.final_project.models.favoriteHotel.favoriteHotelNew.Hotel
import com.example.final_project.ui.activities.detailHotel.DetailHotelActivity
import com.squareup.picasso.Picasso

class FavoriteHotelAdapter:RecyclerView.Adapter<FavoriteHotelAdapter.FavoriteHotelViewHolder>() {
    inner class FavoriteHotelViewHolder(val binding: ItemFavoriteHotelBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemFavoriteHotelBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHotelViewHolder {
        binding = ItemFavoriteHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHotelViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Hotel>() {
        override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoriteHotelViewHolder, position: Int) {

        val hotel = differ.currentList[position]

        holder.itemView.apply {
            binding.tvHotelLocation.text = hotel.newhotelsHotelId.hotelCity
            binding.tvNameHotel.text = hotel.newhotelsHotelId.hotelName
            binding.tvHotelPrice.text = hotel.newhotelsHotelId.minPrice.toString()
            binding.tvPropertyType.text = hotel.newhotelsHotelId.propertyType
            Picasso.get().load(hotel.newhotelsHotelId.hotelImage).into(binding.ivThumbnailHotel)

            setOnClickListener {
                val intent = Intent(it.context, DetailHotelActivity::class.java)
                intent.putExtra("HOTEL_ID", hotel.newhotelsHotelId.hotelId)
                it.context.startActivity(intent)
            }
        }

    }
}