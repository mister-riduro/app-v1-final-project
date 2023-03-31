package com.example.final_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_project.databinding.ItemFavoriteHotelBinding
import com.example.final_project.models.favoriteHotel.FavoriteDetailHotel
import com.example.final_project.models.favoriteHotel.FavoriteHotel
import com.example.final_project.models.favoriteTourism.FavoriteTourism
import com.example.final_project.ui.activities.DetailTourismActivity
import com.example.final_project.ui.activities.detailHotel.DetailHotelActivity
import com.squareup.picasso.Picasso

class FavoriteHotelAdapter:RecyclerView.Adapter<FavoriteHotelAdapter.FavoriteHotelViewHolder>() {
    inner class FavoriteHotelViewHolder(val binding: ItemFavoriteHotelBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemFavoriteHotelBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHotelViewHolder {
        binding = ItemFavoriteHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHotelViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<FavoriteHotel>() {
        override fun areItemsTheSame(oldItem: FavoriteHotel, newItem: FavoriteHotel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoriteHotel, newItem: FavoriteHotel): Boolean {
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
            binding.tvHotelLocation.text = hotel.hotelsHotelID.hotelCity
            binding.tvNameHotel.text = hotel.hotelsHotelID.hotelName
            binding.tvHotelPrice.text = hotel.hotelsHotelID.minPrice.toString()
            binding.tvPropertyType.text = hotel.hotelsHotelID.propertyType
            Picasso.get().load(hotel.hotelsHotelID.hotelImage).into(binding.ivThumbnailHotel)

            setOnClickListener {
                val intent = Intent(it.context, DetailHotelActivity::class.java)
                intent.putExtra("HOTEL_ID", hotel.hotelsHotelID.hotelID)
                it.context.startActivity(intent)
            }
        }

    }
}