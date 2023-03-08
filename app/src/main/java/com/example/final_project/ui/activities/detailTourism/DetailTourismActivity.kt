package com.example.final_project.ui.activities

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.final_project.R
import com.example.final_project.databinding.ActivityTourismDetailBinding
import com.example.final_project.models.dto.TourismFacilitiesResponse
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.detailTourism.DetailTourismViewModel
import com.example.final_project.ui.activities.detailTourism.DetailTourismViewModelFactory
import com.example.final_project.util.Resource
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip

class DetailTourismActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTourismDetailBinding
    private lateinit var detailTourismViewModel: DetailTourismViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTourismDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val payload = intent.getLongExtra("tourismID", 1)
        val detailTourismViewModelFactory = DetailTourismViewModelFactory(repository, application,  payload)

        val toggleButton: ToggleButton = binding.toggleFavorite
        toggleButton.background = getDrawable(R.drawable.icon_favorite_outline)

        detailTourismViewModel = ViewModelProvider(this, detailTourismViewModelFactory).get(
            DetailTourismViewModel::class.java)

        fetchData(payload, toggleButton)
        supportActionBar?.hide()

        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun fetchData(tourismID: Long, toggleButton: ToggleButton) {
        detailTourismViewModel.getDetailTourism(tourismID)
        detailTourismViewModel._tourismLiveData.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
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

                    toggleButton.setOnCheckedChangeListener {_, isChecked ->
                        if (isChecked) {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_filled)
                        } else {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_outline)
                        }
                    }

                    createFacilities(response.data?.facilities)
                }
                is Resource.Error -> {
                    // show error message
                }
                is Resource.Loading -> {
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