package com.example.final_project.adapters

import android.content.Intent
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourismAreaViewHolder {
        val binding = ItemTourismAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourismAreaViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Province>() {
        override fun areItemsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    private lateinit var binding: ItemTourismAreaBinding

    override fun onBindViewHolder(holder: TourismAreaViewHolder, position: Int) {
        val tourism = differ.currentList[position]

        holder.itemView.apply {
            Picasso.get().load(tourism.provinceImage).into(binding.ivTourismArea)
            binding.tvArea.text = tourism.provinceName

            setOnClickListener {
                val intent = Intent(it.context, AreaBasedTourismActivity::class.java)

                intent.putExtra("provinceID", tourism.provinceID)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}