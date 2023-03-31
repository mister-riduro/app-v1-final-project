package com.example.final_project.ui.activities.detailHotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.ToggleButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.R
import com.example.final_project.adapters.HotelFacilitiesAdapter
import com.example.final_project.databinding.ActivityHotelDetailBinding
import com.example.final_project.models.favoriteHotel.FavoriteHotelID
import com.example.final_project.models.favoriteHotel.UpdateFavHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.CreateUserFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.UpdateUserFavoriteHotelBody
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import com.squareup.picasso.Picasso

class DetailHotelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHotelDetailBinding
    private lateinit var hotelDetailHotelViewModel: DetailHotelViewModel
    private lateinit var hotelFacilitiesAdapter: HotelFacilitiesAdapter
    private lateinit var toggleButton: ToggleButton
    private lateinit var userID: String

    private var hotelID: Long = 0
    var userFavHotelID: Long = 0
    private var favID: Long = 0

    private val hotelHotelID = mutableListOf <FavoriteHotelID>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        hotelID = intent.getLongExtra("HOTEL_ID", 1)
        val detailHotelViewModelFactory = DetailHotelViewModelFactory(repository, application)
        hotelDetailHotelViewModel = ViewModelProvider(this, detailHotelViewModelFactory).get(DetailHotelViewModel::class.java)

        userID = hotelDetailHotelViewModel.getUserID()

        toggleButton = binding.toggleFavorite
        toggleButton.background = getDrawable(R.drawable.icon_favorite_outline)

        setupRecyclerView()

        getFavoriteHotelID()
        getUserFavoriteHotel()
        fetchDataHotel()

        supportActionBar?.hide()
        binding.ivArrowBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getUserFavoriteHotel() {
        hotelDetailHotelViewModel.getUserFavoriteHotel(hotelID, userID)
        hotelDetailHotelViewModel._getUserFavHotelLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    if (response.data?.data?.size == 0) {
                        createUserFavoriteHotel(hotelID)
                    } else {
                        toggleButton.isChecked = response.data?.data!![0].isFavorite

                        if (toggleButton.isChecked) {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_filled)
                        } else {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_outline)
                        }

                        userFavHotelID = response.data.data[0].id
                    }
                }
            }
        })

    }

    private fun createUserFavoriteHotel(tourismID: Long) {
        hotelDetailHotelViewModel.createUserFavoriteHotel(CreateUserFavoriteHotelBody(userID, tourismID, false))
        hotelDetailHotelViewModel._createUserFavHotelLiveData.observe(this, Observer { response ->
            when (response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    userFavHotelID = response.data?.data?.id!!
                }
            }
        })
    }

    private fun updateUserFavoriteHotel(isFavorite: Boolean) {
        val updateUserFavHotelBody = UpdateUserFavoriteHotelBody(isFavorite)
        hotelDetailHotelViewModel.updateUserFavoriteHotel(userFavHotelID,updateUserFavHotelBody)
        hotelDetailHotelViewModel._updateUserFavHotelLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                }
            }
        })
    }

    private fun getFavoriteHotelID() {
        hotelDetailHotelViewModel.getFavoriteHotel(hotelDetailHotelViewModel.getUserID())
        hotelDetailHotelViewModel._favTourismID.observe(this) {
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

    private fun removeFavoriteHotel(userID: String, hotelID: Long?) {
        hotelDetailHotelViewModel.getFavoriteHotel(userID)
        hotelDetailHotelViewModel._favHotelLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Error -> {
                    Log.d("ERROR FAV TOURISM", "Error retrieve favorite tourism data")
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val tourisms = response.data?.data?.get(0)?.hotels
                    Log.d("HOTELS DATA", "$tourisms")

                    if (tourisms!!.isNotEmpty()) {
                        tourisms.forEach {
                            hotelHotelID.add(FavoriteHotelID(it.hotelsHotelID.hotelID))
                        }
                    }

                    Log.d("TOURISMTOURISMID 1", "${hotelHotelID.size}")

                    tourisms.forEach {
                        if (hotelID == it.hotelsHotelID.hotelID) {
                            hotelHotelID.remove(FavoriteHotelID(it.hotelsHotelID.hotelID))
                        }
                    }

                    Log.d("TOURISMTOURISMID 2", "${hotelHotelID.size}")

                    Log.d("TOURISMTOURISMID BODY", "${hotelHotelID.toList()}")

                    updateFavorite(hotelHotelID.toList(), favID)

                    hotelHotelID.clear()
                }
            }

        })
    }

    private fun updateFavorite(hotelHotelID: List<FavoriteHotelID>, favoriteID: Long?) {
        val updateFavHotelBody = UpdateFavHotelBody(hotelHotelID)

        Log.d("UPFAV TOURISM BODY", "$updateFavHotelBody")

        hotelDetailHotelViewModel.updateFavoriteHotel(favoriteID!!, updateFavHotelBody)
        hotelDetailHotelViewModel._updateFavHotelLiveData.observe(this, Observer { response ->
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

    private fun fetchDataHotel() {
        hotelDetailHotelViewModel.getDetailHotel(hotelID)
        hotelDetailHotelViewModel._hotelLiveData.observe(this) { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Picasso.get().load(response.data?.data?.hotelImage).into(binding.imageDetailHotel)
                    binding.tvHotelLocation.text = response.data?.data?.hotelCity
                    binding.tvHotelName.text = response.data?.data?.hotelName
                    binding.tvHotelPrice.text = response.data?.data?.minPrice.toString()

                    binding.tvPropertyType.text = response.data?.data?.propertyType
                    binding.tvRating.text = response.data?.data?.hotelRating.toString()

                    toggleButton.setOnCheckedChangeListener {_, isChecked ->
                        Log.d("CHECK STATE CLICK", "$isChecked")

                        if (isChecked) {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_filled)
                            hotelHotelID.add(FavoriteHotelID(hotelID))
                            updateFavorite(hotelHotelID.toList(), favID)
                            updateUserFavoriteHotel(true)

                        } else {
                            toggleButton.background = getDrawable(R.drawable.icon_favorite_outline)
                            removeFavoriteHotel(userID, hotelID)
                            updateUserFavoriteHotel(false)

                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        hotelFacilitiesAdapter = HotelFacilitiesAdapter()
        binding.gridFacilities.apply {
            adapter = hotelFacilitiesAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}