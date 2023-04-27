package com.example.final_project.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ItemNearestDestinationBinding
import com.example.final_project.models.distance.DistanceData
import com.example.final_project.util.Constants
import com.squareup.picasso.Picasso

class NearestDestinationAdapter: RecyclerView.Adapter<NearestDestinationAdapter.NearestDestinationViewHolder>() {

    inner class NearestDestinationViewHolder(val binding: ItemNearestDestinationBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemNearestDestinationBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearestDestinationAdapter.NearestDestinationViewHolder {
        binding = ItemNearestDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NearestDestinationViewHolder(binding)
    }

    private val differCallback = object : DiffUtil.ItemCallback<DistanceData>() {
        override fun areItemsTheSame(oldItem: DistanceData, newItem: DistanceData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DistanceData, newItem: DistanceData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(
        holder: NearestDestinationAdapter.NearestDestinationViewHolder,
        position: Int
    ) {
        val nearestDestination = differ.currentList[position]

        holder.itemView.apply {
            try {
                val link = "${Constants.BASE_URL}/assets/${nearestDestination.distanceTourismData.tourismImage}"
                Picasso.get().load(link).into(binding.ivTourismArea)
            } catch (e: Exception) {
                Log.d("LOAD IMAGE ERROR", "No Image Data")
            }

            binding.tvTourismName.text = nearestDestination.distanceTourismData.tourismName
            binding.tvTourismType.text = nearestDestination.distanceTourismData.tourismType

            val formattedDistance = String.format("%.2f", nearestDestination.distance).toDouble()
            binding.tvDistance.text = formattedDistance.toString()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}