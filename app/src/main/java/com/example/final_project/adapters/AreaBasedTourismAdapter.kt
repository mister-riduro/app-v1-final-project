package com.example.final_project.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemAreaBasedTourismBinding
import com.example.final_project.models.tourism.DetailTourism
import com.example.final_project.models.tourism.listTourism.ListTourismDetail
import com.example.final_project.ui.activities.DetailTourismActivity
import com.example.final_project.util.Constants
import com.squareup.picasso.Picasso

class AreaBasedTourismAdapter:RecyclerView.Adapter<AreaBasedTourismAdapter.AreaBasedTourismViewHolder>() {
    inner class AreaBasedTourismViewHolder(val binding: ItemAreaBasedTourismBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemAreaBasedTourismBinding

    private val differCallback = object : DiffUtil.ItemCallback<ListTourismDetail>() {
        override fun areItemsTheSame(oldItem: ListTourismDetail, newItem: ListTourismDetail): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ListTourismDetail, newItem: ListTourismDetail): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaBasedTourismViewHolder {
        binding = ItemAreaBasedTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AreaBasedTourismViewHolder(binding)
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onBindViewHolder(holder: AreaBasedTourismViewHolder, position: Int) {
        val tourism = differ.currentList[position]

        holder.itemView.apply {
            try {
                val link = "${Constants.BASE_URL}/assets/${tourism.tourismImage}"
                Picasso.get().load(link).into(binding.ivTourismPicture)
            } catch (e : Exception) {
                Log.d("ERROR IMAGE TOURISM", "Failed load Image")
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

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}