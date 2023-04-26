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
import com.example.final_project.adapters.NearestDestinationAdapter
import com.example.final_project.databinding.ActivityHotelDetailBinding
import com.example.final_project.models.favoriteHotel.FavoriteHotelID
import com.example.final_project.models.favoriteHotel.UpdateFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.CreateUserFavoriteHotelBody
import com.example.final_project.models.favoriteHotel.userFavoriteHotel.UpdateUserFavoriteHotelBody
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import com.squareup.picasso.Picasso

class DetailHotelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHotelDetailBinding
    private lateinit var hotelDetailHotelViewModel: DetailHotelViewModel
    private lateinit var hotelFacilitiesAdapter: HotelFacilitiesAdapter
    private lateinit var nearestDestinationAdapter: NearestDestinationAdapter
    private lateinit var toggleButton: ToggleButton
    private lateinit var userID: String

    private var hotelID: Long = 0
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
        fetchDataHotel()
        fetchDataDestination()

        supportActionBar?.hide()
        binding.ivArrowBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun fetchDataDestination() {
        hotelDetailHotelViewModel.getNearestDestination(hotelID)
        hotelDetailHotelViewModel._nearestDestLiveData.observe(this) { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    nearestDestinationAdapter.differ.submitList(response.data?.data)
                }
            }
        }
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

                    hotelFacilitiesAdapter.differ.submitList(response.data?.data?.facilities)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        nearestDestinationAdapter = NearestDestinationAdapter()
        binding.rvDestinasiTerdekat.apply {
            adapter = nearestDestinationAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }


        hotelFacilitiesAdapter = HotelFacilitiesAdapter()
        binding.gridFacilities.apply {
            adapter = hotelFacilitiesAdapter
            layoutManager = GridLayoutManager(this.context, 3)
        }
    }
}