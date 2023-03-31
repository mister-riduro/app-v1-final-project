package com.example.final_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemFavoriteTourismBinding
import com.example.final_project.models.favoriteTourism.FavoriteTourism
import com.example.final_project.ui.activities.DetailTourismActivity
import com.squareup.picasso.Picasso

class FavoriteTourismAdapter: RecyclerView.Adapter<FavoriteTourismAdapter.FavoriteTourismViewHolder>() {
    inner class FavoriteTourismViewHolder(val binding: ItemFavoriteTourismBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemFavoriteTourismBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTourismViewHolder {
        binding = ItemFavoriteTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTourismViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<FavoriteTourism>() {
        override fun areItemsTheSame(oldItem: FavoriteTourism, newItem: FavoriteTourism): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoriteTourism, newItem: FavoriteTourism): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onBindViewHolder(holder: FavoriteTourismViewHolder, position: Int) {
        val tourism = differ.currentList[position]

        holder.itemView.apply {

            Picasso.get().isLoggingEnabled = true
            Picasso.get().load(tourism.tourismsTourismID.tourismImage).into(binding.ivTourismPicture)

            binding.tvAbTourismName.text = tourism.tourismsTourismID.tourismName
            binding.chipTourismType.text = tourism.tourismsTourismID.tourismType
            binding.tvAbTourismLocation.text = tourism.tourismsTourismID.tourismAddress
            binding.tvAbRating.text = tourism.tourismsTourismID.tourismRating.toString()

            setOnClickListener {
                val intent = Intent(it.context, DetailTourismActivity::class.java)
                intent.putExtra("tourismID", tourism.tourismsTourismID.tourismID)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}