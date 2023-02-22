package com.example.final_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_project.databinding.ItemFavoriteTourismBinding
import com.example.final_project.models.DetailTourism
import com.example.final_project.models.`object`.DetailTourismObjects
import com.example.final_project.ui.activities.DetailTourismActivity

class FavoriteTourismAdapter(private val detail_tourism: ArrayList<DetailTourism>): RecyclerView.Adapter<FavoriteTourismAdapter.FavoriteTourismViewHolder>() {
    inner class FavoriteTourismViewHolder(val binding: ItemFavoriteTourismBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTourismViewHolder {
        val binding = ItemFavoriteTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTourismViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteTourismViewHolder, position: Int) {
        with(holder) {
            with(detail_tourism[position]) {
                Glide.with(itemView.context).load(this.tourismImage).into(binding.ivTourismPicture)
                binding.tvAbTourismName.text = this.tourismName
                binding.chipTourismType.text = this.tourismType
                binding.tvAbTourismLocation.text = this.tourismAddress
                binding.tvAbRating.text = this.tourismRating.toString()

                holder.itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailTourismActivity::class.java)

//                    intent.putExtra("tourism_detail", DetailTourismObjects.detail_tourism_objects[position])
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return detail_tourism.size
    }
}