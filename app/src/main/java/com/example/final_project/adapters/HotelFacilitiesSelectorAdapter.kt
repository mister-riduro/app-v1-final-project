package com.example.final_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.AsyncListDiffer
import com.example.final_project.databinding.ItemHotelFacilitiesSelectionBinding
import com.example.final_project.models.hotel.HotelFacilitiesSelection
import com.squareup.picasso.Picasso

class HotelFacilitiesSelectorAdapter(): RecyclerView.Adapter<HotelFacilitiesSelectorAdapter.HotelFacilitesSelectorViewHolder>() {

    inner class HotelFacilitesSelectorViewHolder(val binding: ItemHotelFacilitiesSelectionBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemHotelFacilitiesSelectionBinding

    private val selectedItems = ArrayList<String>()

    private val differCallback = object : DiffUtil.ItemCallback<HotelFacilitiesSelection>() {
        override fun areItemsTheSame(oldItem: HotelFacilitiesSelection, newItem: HotelFacilitiesSelection): Boolean {
            return oldItem.hfacilitiesId == newItem.hfacilitiesId
        }

        override fun areContentsTheSame(oldItem: HotelFacilitiesSelection, newItem: HotelFacilitiesSelection): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelFacilitesSelectorViewHolder {
        binding = ItemHotelFacilitiesSelectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelFacilitesSelectorViewHolder(binding)
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: HotelFacilitesSelectorViewHolder, position: Int) {
        val facilities = differ.currentList[position]
        holder.itemView.apply {
            Picasso.get().load(facilities.facilitiesImage).into(binding.ivHotelFacilities)
            binding.tvHotelFacilities.text = facilities.facilitiesName
            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedItems.add(facilities.facilitiesName)
                } else {
                    selectedItems.remove(facilities.facilitiesName)
                }
            }
        }
    }

    fun getSelectedItems(): ArrayList<String> {
        return  selectedItems
    }
}