//package com.example.final_project.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.final_project.databinding.ItemTourismFacilitiesBinding
//import com.example.final_project.models.TourismFacilities
//
//class TourismFacilitiesAdapter(private val tourism_facilities: ArrayList<TourismFacilities>):RecyclerView.Adapter<TourismFacilitiesAdapter.TourismFacilitiesViewHolder>() {
//    inner class TourismFacilitiesViewHolder(val binding: ItemTourismFacilitiesBinding): RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourismFacilitiesViewHolder {
//        val binding = ItemTourismFacilitiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return TourismFacilitiesViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: TourismFacilitiesViewHolder, position: Int) {
//        with(holder) {
//            with(tourism_facilities[position]) {
//                binding.tvFacilities.text = this.facilities_name
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return tourism_facilities.size
//    }
//
//}