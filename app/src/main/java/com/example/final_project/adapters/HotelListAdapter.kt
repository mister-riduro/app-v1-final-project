package com.example.final_project.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemFavoriteHotelBinding
import com.example.final_project.models.hotel.hotelList.HotelList
import com.example.final_project.ui.activities.detailHotel.DetailHotelActivity
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class HotelListAdapter: RecyclerView.Adapter<HotelListAdapter.HotelListViewHolder>() {
    inner class HotelListViewHolder(val binding: ItemFavoriteHotelBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemFavoriteHotelBinding

    private val differCallback = object : DiffUtil.ItemCallback<HotelList>() {
        override fun areItemsTheSame(oldItem: HotelList, newItem: HotelList): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HotelList, newItem: HotelList): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelListViewHolder {
        binding = ItemFavoriteHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelListViewHolder(binding)
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onBindViewHolder(holder: HotelListViewHolder, position: Int) {
        val hotel = differ.currentList[position]

        holder.itemView.apply {
            Picasso.get().load(hotel.hotelImage).into(binding.ivThumbnailHotel)
            binding.tvNameHotel.text = hotel.hotelName
            binding.tvHotelLocation.text = hotel.hotelCity

            val formattedPrice: NumberFormat = NumberFormat.getCurrencyInstance()
            formattedPrice.maximumFractionDigits = 0
            formattedPrice.currency = Currency.getInstance("IDR")

            binding.tvHotelPrice.text = formattedPrice.format(hotel.minPrice).toString()
            binding.tvRating.text = hotel.hotelRating.toString()

            setOnClickListener {
                val intent = Intent(it.context, DetailHotelActivity::class.java)

                Log.d("HOTEL ID", "${hotel.hotelID}")

                intent.putExtra("HOTEL_ID", hotel.hotelID)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}