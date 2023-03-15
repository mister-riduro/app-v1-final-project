package com.example.final_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemTypeBasedTourismBinding
import com.example.final_project.models.tourism.DetailTourism
import com.example.final_project.ui.activities.DetailTourismActivity
import com.squareup.picasso.Picasso

class TypeBasedTourismAdapter: RecyclerView.Adapter<TypeBasedTourismAdapter.TypeBasedTourismViewHolder>() {
    inner class TypeBasedTourismViewHolder(val binding: ItemTypeBasedTourismBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<DetailTourism>() {
        override fun areItemsTheSame(oldItem: DetailTourism, newItem: DetailTourism): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DetailTourism, newItem: DetailTourism): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    private lateinit var binding: ItemTypeBasedTourismBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeBasedTourismViewHolder {
        binding = ItemTypeBasedTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TypeBasedTourismViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TypeBasedTourismViewHolder, position: Int) {
        val tourism = differ.currentList[position]

        holder.itemView.apply {
            Picasso.get().load(tourism.tourismImage).into(binding.ivTourismPicture)
            binding.tvAbTourismName.text = tourism.tourismName
            binding.chipTourismType.text = tourism.tourismType
            binding.tvAbTourismLocation.text = tourism.tourismAddress
            binding.tvAbRating.text = tourism.tourismRating.toString()

            setOnClickListener {
                val intent = Intent(it.context, DetailTourismActivity::class.java)

                intent.putExtra("tourismID", tourism.tourismID)
                it.context.startActivity(intent)
            }
        }
    }
}