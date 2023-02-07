package com.example.final_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_project.databinding.ItemFavoriteHotelBinding
import com.example.final_project.models.FavoriteHotel

class FavoriteHotelAdapter(private val favorite_hotel: ArrayList<FavoriteHotel>):RecyclerView.Adapter<FavoriteHotelAdapter.FavoriteHotelViewHolder>() {
    inner class FavoriteHotelViewHolder(val binding: ItemFavoriteHotelBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHotelViewHolder {
        val binding = ItemFavoriteHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHotelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favorite_hotel.size
    }

    override fun onBindViewHolder(holder: FavoriteHotelViewHolder, position: Int) {
        with(holder) {
            with(favorite_hotel[position]) {
                Glide.with(itemView.context).load(this.hotelImage).into(binding.ivThumbnailHotel)
                binding.tvHotelLocation.text = this.hotelCity
                binding.tvNameHotel.text = this.hotelName
                binding.tvHotelPrice.text = this.minPrice.toString()
                binding.tvPropertyType.text =this.propertyType
            }
        }
    }
}