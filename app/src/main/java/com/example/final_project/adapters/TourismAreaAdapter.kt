package com.example.final_project.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemTourismAreaBinding
import com.example.final_project.models.DetailTourism
import com.example.final_project.models.Province
import com.example.final_project.ui.activities.DetailTourismActivity
import com.example.final_project.ui.activities.areaBasedTourism.AreaBasedTourismActivity
import com.squareup.picasso.Picasso

class TourismAreaAdapter: RecyclerView.Adapter<TourismAreaAdapter.TourismAreaViewHolder>() {
    inner class TourismAreaViewHolder(val binding: ItemTourismAreaBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemTourismAreaBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourismAreaViewHolder {
        binding = ItemTourismAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourismAreaViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Province>() {
        override fun areItemsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem.provinceName == newItem.provinceName
        }

        override fun areContentsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: TourismAreaViewHolder, position: Int) {
        val tourism = differ.currentList[position]

        holder.itemView.apply {
            Picasso.get().isLoggingEnabled = true
            Picasso.get().load(tourism.provinceImage).into(binding.ivTourismArea)
            binding.tvArea.text = tourism.provinceName

            Log.d("TV AREA", binding.tvArea.text.toString())

            setOnClickListener {
                val intent = Intent(it.context, AreaBasedTourismActivity::class.java)

                intent.putExtra("provinceName", tourism.provinceName)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}