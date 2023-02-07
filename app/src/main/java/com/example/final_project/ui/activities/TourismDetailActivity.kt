package com.example.final_project.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.final_project.R
import com.example.final_project.databinding.ActivityTourismDetailBinding
import com.example.final_project.models.DetailTourism
import com.google.android.material.bottomsheet.BottomSheetDialog
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

        val tourismDescription = payload.tourism_description
        binding.tvSeeMoreTourismDesc.setOnClickListener {
            showDialogTourismDesc(tourismDescription)
        }

        val route = payload.tourism_route
        val timeTaken = payload.tourism_travel_time
        val roadCondition = payload.tourism_road_condition
        binding.tvSeeMoreRouteInformation.setOnClickListener {
            showDialogOtherInformation(route, timeTaken, roadCondition)
        }

        supportActionBar?.hide()

        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showDialogTourismDesc(desc: String) {
        val dialog = BottomSheetDialog(this)

        dialog.setContentView(R.layout.dialog_tourism_desc)
        val btnClose = dialog.findViewById<ImageView>(R.id.btn_close_dialog)
        val dialogDesc = dialog.findViewById<TextView>(R.id.tv_dialog_description)

        dialogDesc?.text = desc

        btnClose?.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showDialogOtherInformation(route: String, time: String, road: String) {
        val dialog = BottomSheetDialog(this)

        dialog.setContentView(R.layout.dialog_tourism_route_information)
        val btnClose = dialog.findViewById<ImageView>(R.id.btn_close_dialog)
        val dialogRouteDesc = dialog.findViewById<TextView>(R.id.tv_detail_route_information)
        val dialogTimeDesc = dialog.findViewById<TextView>(R.id.tv_detail_time_taken)
        val dialogRoadDesc = dialog.findViewById<TextView>(R.id.tv_detail_road_condition)

        dialogRouteDesc?.text = route
        dialogTimeDesc?.text = time
        dialogRoadDesc?.text = road

        btnClose?.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
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