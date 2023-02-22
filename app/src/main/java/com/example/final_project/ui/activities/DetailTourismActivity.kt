package com.example.final_project.ui.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.final_project.R
import com.example.final_project.databinding.ActivityTourismDetailBinding
import com.example.final_project.models.DetailTourism
import com.example.final_project.models.dto.TourismFacilitiesResponse
import com.example.final_project.remote.network.NetworkResult
import com.example.final_project.viewmodel.DetailTourismViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip

class DetailTourismActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTourismDetailBinding
    private val detailTourismViewModel by viewModels<DetailTourismViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTourismDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val payload = intent.getParcelableExtra<DetailTourism>("tourismID")!!

        fetchData(payload.tourismID)
        supportActionBar?.hide()

        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun fetchData(tourismID: Long) {
        detailTourismViewModel.fetchTourismResponse(tourismID)
        detailTourismViewModel.tourismLiveData.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.tvDetailAddress.text = response.data?.tourismAddress
                    binding.tvTypeTourism.text = response.data?.tourismType
                    binding.tvTourismLocation.text = response.data?.tourismCity
                    binding.tvDetailTourismDesc.text = response.data?.tourismDescription
                    binding.tvTourismName.text = response.data?.tourismName
                    binding.tvRating.text = response.data?.tourismRating.toString()
                    binding.tvOpenHour.text = response.data?.openHour
                    binding.tvDetailTicketPrice.text = response.data?.entryPrice.toString()

                    val tourismDescription = response.data?.tourismDescription
                    binding.tvSeeMoreTourismDesc.setOnClickListener {
                        showDialogTourismDesc(tourismDescription)
                    }

                    val timeTaken = response.data?.travelingTime
                    val roadCondition = response.data?.roadCondition
                    binding.tvSeeMoreRouteInformation.setOnClickListener {
                        showDialogOtherInformation("", timeTaken, roadCondition)
                    }

                    createFacilities(response.data?.facilities)
                }
                is NetworkResult.Error -> {
                    // show error message
                }
                is NetworkResult.Loading -> {
                    // show a progress bar
                }
            }
        }
    }
    private fun showDialogTourismDesc(desc: String?) {
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

    private fun showDialogOtherInformation(route: String?, time: String?, road: String?) {
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

    private fun createFacilities(facilities: List<TourismFacilitiesResponse?>?) {
        if (facilities != null) {
            for (facils in facilities) {
                val chip = Chip(this)
                chip.isClickable = false
                chip.text = facils?.tfacilitiesTfacilitiesId?.facilitiesName
                binding.chipFacilities.addView(chip)
            }
        }
    }
}