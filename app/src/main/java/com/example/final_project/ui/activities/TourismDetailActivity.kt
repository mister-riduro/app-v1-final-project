package com.example.final_project.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.final_project.databinding.ActivityTourismDetailBinding
import com.example.final_project.models.DetailTourism
import com.google.android.material.chip.Chip

class TourismDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTourismDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTourismDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val payload = intent.getParcelableExtra<DetailTourism>("tourism_detail")!!

        binding.tvDetailAddress.text = payload.tourism_address
        binding.tvTypeTourism.text = payload.tourism_type
        binding.tvTourismLocation.text = payload.tourism_location
        binding.tvDetailTourismDesc.text = payload.tourism_description
        binding.tvTourismName.text = payload.tourism_name
        binding.tvRating.text = payload.tourism_rating.toString()
        binding.tvOpenHour.text = payload.tourism_open_hour
        binding.tvDetailTicketPrice.text = payload.tourism_ticket_price
        binding.tvDetailRuteTransportasi.text = payload.tourism_route
        binding.imageDetailTourism.setImageResource(payload.tourism_image)

        createFacilities()

        supportActionBar?.hide()

        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun createFacilities() {
        val payload = intent.getParcelableExtra<DetailTourism>("tourism_detail")!!

        for (facilities in payload.tourism_facilities) {
            val chip = Chip(this)
            chip.isClickable = false
            chip.text = facilities.facilities_name
            binding.chipFacilities.addView(chip)
        }
    }
}