package com.example.final_project.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemTypeBasedTourismBinding
import com.example.final_project.models.tourism.DetailTourism
import com.example.final_project.models.tourism.listTourism.ListTourismDetail
import com.example.final_project.ui.activities.DetailTourismActivity
import com.example.final_project.util.Constants.Companion.BASE_URL
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso

class TypeBasedTourismAdapter: RecyclerView.Adapter<TypeBasedTourismAdapter.TypeBasedTourismViewHolder>() {
    inner class TypeBasedTourismViewHolder(val binding: ItemTypeBasedTourismBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<ListTourismDetail>() {
        override fun areItemsTheSame(oldItem: ListTourismDetail, newItem: ListTourismDetail): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ListTourismDetail, newItem: ListTourismDetail): Boolean {
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
            try {
                val link = "$BASE_URL/assets/${tourism.tourismImage}"
                Picasso.get().load(link).into(binding.ivTourismPicture)
            } catch (e:Exception) {
                Log.d("ERROR IMAGE TOURISM", "Failed load tourism image")
            }

            binding.chipTourismType.isClickable = false

            binding.tvAbTourismName.text = tourism.tourismName
            binding.chipTourismType.text = tourism.tourismType
            binding.tvAbTourismLocation.text = tourism.tourismCity
            binding.tvAbRating.text = tourism.tourismRating.toString()

            setOnClickListener {
                val intent = Intent(it.context, DetailTourismActivity::class.java)

                intent.putExtra("tourismID", tourism.tourismId)
                it.context.startActivity(intent)
            }
        }
    }
}