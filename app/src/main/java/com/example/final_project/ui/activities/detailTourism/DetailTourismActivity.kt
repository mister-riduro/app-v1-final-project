package com.example.final_project.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.adapters.RoutesListAdapter
import com.example.final_project.databinding.ActivityTourismDetailBinding
import com.example.final_project.models.Routes
import com.example.final_project.models.dto.TourismFacilitiesResponse
import com.example.final_project.models.favoriteTourism.FavoriteTourismID
import com.example.final_project.models.favoriteTourism.UpdateFavTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.CreateUserFavoriteTourismBody
import com.example.final_project.models.favoriteTourism.userFavoriteTourism.UpdateUserFavoriteTourismBody
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.detailTourism.DetailTourismViewModel
import com.example.final_project.ui.activities.detailTourism.DetailTourismViewModelFactory
import com.example.final_project.util.Resource
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip

class DetailTourismActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTourismDetailBinding
    private lateinit var detailTourismViewModel: DetailTourismViewModel
    private lateinit var routesAdapter: RoutesListAdapter
    private lateinit var toggleButton: ToggleButton
    private lateinit var userID: String

    var favID: Long = 0
    var userFavTourismID: Long = 0
    var tourismID: Long = 0

    private val tourismTourismID = mutableListOf <FavoriteTourismID>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTourismDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val detailTourismViewModelFactory = DetailTourismViewModelFactory(repository, application)

        detailTourismViewModel = ViewModelProvider(this, detailTourismViewModelFactory).get(
            DetailTourismViewModel::class.java)

        userID = detailTourismViewModel.getUserID()
        tourismID = intent.getLongExtra("tourismID", 1)

        toggleButton = binding.toggleFavorite
        toggleButton.background = getDrawable(R.drawable.icon_favorite_outline)

        getFavoriteTourismID()
        getUserFavoriteTourism()
        fetchDataTourism()

        supportActionBar?.hide()

        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getUserFavoriteTourism() {
        detailTourismViewModel.getUserFavoriteTourism(tourismID, userID)
        detailTourismViewModel._getUserFavTourismLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    if (response.data?.data?.size == 0) {
                        createUserFavoriteTourism(tourismID)
                    } else {
                        toggleButton.isChecked = response.data?.data!![0].isFavorite

                        if (toggleButton.isChecked) {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_filled)
                        } else {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_outline)
                        }

                        userFavTourismID = response.data.data[0].id
                    }
                }
            }
        })

    }

    private fun createUserFavoriteTourism(tourismID: Long) {
        detailTourismViewModel.createUserFavoriteTourism(CreateUserFavoriteTourismBody(userID, tourismID, false))
        detailTourismViewModel._createUserFavTourismLiveData.observe(this, Observer { response ->
            when (response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    userFavTourismID = response.data?.data?.id!!
                    Toast.makeText(this, "Success Create User Fav Tourism", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun updateUserFavoriteTourism(isFavorite: Boolean) {
        val updateUserFavTourismBody = UpdateUserFavoriteTourismBody(isFavorite)
        detailTourismViewModel.updateUserFavoriteTourism(userFavTourismID,updateUserFavTourismBody)
        detailTourismViewModel._updateUserFavTourismLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Toast.makeText(this, "Success Update User Fav Tourism", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getFavoriteTourismID() {
        detailTourismViewModel.getFavoriteTourism(detailTourismViewModel.getUserID())
        detailTourismViewModel._favTourismID.observe(this) {
            when(it) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    favID = it.data!!
                }
            }
        }
    }

    private fun fetchDataTourism() {
        detailTourismViewModel.getDetailTourism(tourismID)
        detailTourismViewModel._tourismLiveData.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.tvDetailAddress.text = response.data?.data?.tourismAddress
                    binding.tvTypeTourism.text = response.data?.data?.tourismType
                    binding.tvTourismLocation.text = response.data?.data?.tourismCity
                    binding.tvDetailTourismDesc.text = response.data?.data?.tourismDescription
                    binding.tvTourismName.text = response.data?.data?.tourismName
                    binding.tvRating.text = response.data?.data?.tourismRating.toString()
                    binding.tvOpenHour.text = response.data?.data?.openHour
                    binding.tvDetailTicketPrice.text = response.data?.data?.entryPrice.toString()

                    val tourismDescription = response.data?.data?.tourismDescription
                    binding.tvSeeMoreTourismDesc.setOnClickListener {
                        showDialogTourismDesc(tourismDescription)
                    }

                    val timeTaken = response.data?.data?.travelingTime
                    val roadCondition = response.data?.data?.roadCondition
                    val routes = response.data?.data?.routes


                    binding.tvSeeMoreRouteInformation.setOnClickListener {
                        showDialogOtherInformation(routes, timeTaken, roadCondition)
                    }

                    toggleButton.setOnCheckedChangeListener {_, isChecked ->
                        Log.d("CHECK STATE CLICK", "$isChecked")

                        if (isChecked) {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_filled)
                            tourismTourismID.add(FavoriteTourismID(tourismID))
                            updateFavorite(tourismTourismID.toList(), favID)
                            updateUserFavoriteTourism(true)

                        } else {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_outline)
                            removeFavoriteTourism(userID, tourismID)
                            updateUserFavoriteTourism(false)

                        }

//                        refreshActivity()
                    }
                    createFacilities(response.data?.data?.facilities)
                }
                is Resource.Error -> {
                    Toast.makeText(this, "Error retrieve tourism data", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
            }
        }
    }

    private fun refreshActivity() {
        recreate()
    }

    private fun removeFavoriteTourism(userID: String, tourismID: Long?) {
        detailTourismViewModel.getFavoriteTourism(userID)
        detailTourismViewModel._favTourismLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Error -> {
                    Log.d("ERROR FAV TOURISM", "Error retrieve favorite tourism data")
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val tourisms = response.data?.data?.get(0)?.tourisms
                    Log.d("TOURISMS DATA", "$tourisms")

                    if (tourisms!!.isNotEmpty()) {
                        tourisms.forEach {
                            tourismTourismID.add(FavoriteTourismID(it.tourismsTourismID.tourismID))
                        }
                    }

                    Log.d("TOURISMTOURISMID 1", "${tourismTourismID.size}")

                    tourisms.forEach {
                        if (tourismID == it.tourismsTourismID.tourismID) {
                            tourismTourismID.remove(FavoriteTourismID(it.tourismsTourismID.tourismID))
                        }
                    }

                    Log.d("TOURISMTOURISMID 2", "${tourismTourismID.size}")

                    Log.d("TOURISMTOURISMID BODY", "${tourismTourismID.toList()}")

                    updateFavorite(tourismTourismID.toList(), favID)

                    tourismTourismID.clear()
                }
            }

        })
    }

    private fun updateFavorite(tourismTourismID: List<FavoriteTourismID>, favoriteID: Long?) {
        val updateFavTourismBody = UpdateFavTourismBody(tourismTourismID)

        Log.d("UPFAV TOURISM BODY", "$updateFavTourismBody")

        detailTourismViewModel.updateFavoriteTourism(favoriteID!!, updateFavTourismBody)
        detailTourismViewModel._updateFavTourismLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Error -> {
                    Log.d("ERROR FAV TOURISM", "Error update favorite tourism data")
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Toast.makeText(this, "Success update favorite tourism", Toast.LENGTH_SHORT).show()
                }
            }
        })
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

    private fun showDialogOtherInformation(routes: List<Routes?>?, time: String?, road: String?) {
        val dialog = BottomSheetDialog(this)

        dialog.setContentView(R.layout.dialog_tourism_route_information)
        val btnClose = dialog.findViewById<ImageView>(R.id.btn_close_dialog)
        val dialogRouteDesc = dialog.findViewById<RecyclerView>(R.id.tv_detail_route_information)
        val dialogTimeDesc = dialog.findViewById<TextView>(R.id.tv_detail_time_taken)
        val dialogRoadDesc = dialog.findViewById<TextView>(R.id.tv_detail_road_condition)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dialogRouteDesc?.layoutManager = layoutManager

        routesAdapter = RoutesListAdapter(routes)
        dialogRouteDesc?.adapter = routesAdapter

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