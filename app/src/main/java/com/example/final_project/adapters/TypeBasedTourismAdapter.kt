package com.example.final_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemTypeBasedTourismBinding
import com.example.final_project.models.DetailTourism
import com.example.final_project.ui.activities.DetailTourismActivity
import com.squareup.picasso.Picasso

class TypeBasedTourismAdapter(private val detail_tourism: ArrayList<DetailTourism>): RecyclerView.Adapter<TypeBasedTourismAdapter.TypeBasedTourismViewHolder>() {
    inner class TypeBasedTourismViewHolder(val binding: ItemTypeBasedTourismBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeBasedTourismViewHolder {
        val binding = ItemTypeBasedTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TypeBasedTourismViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return detail_tourism.size
    }

    override fun onBindViewHolder(holder: TypeBasedTourismViewHolder, position: Int) {
        with(holder) {
            with(detail_tourism[position]) {
                Picasso.get().load(this.tourismImage).into(binding.ivTourismPicture)
                binding.tvAbTourismName.text = this.tourismName
                binding.chipTourismType.text = this.tourismType
                binding.tvAbTourismLocation.text = this.tourismAddress
                binding.tvAbRating.text = this.tourismRating.toString()

                holder.itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailTourismActivity::class.java)

                    intent.putExtra("tourismID", this.tourismID)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}