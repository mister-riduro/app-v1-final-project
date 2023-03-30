package com.example.final_project.ui.activities.detailHotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.final_project.R
import com.example.final_project.adapters.AreaBasedTourismAdapter
import com.example.final_project.adapters.HotelFacilitiesAdapter
import com.example.final_project.databinding.ActivityHotelDetailBinding
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import com.squareup.picasso.Picasso

class DetailHotelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHotelDetailBinding
    private lateinit var hotelDetailHotelViewModel: DetailHotelViewModel
    private lateinit var hotelFacilitiesAdapter: HotelFacilitiesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val payload = intent.getLongExtra("HOTEL_ID", 1)
        val detailHotelViewModelFactory = DetailHotelViewModelFactory(repository, application)

        setupRecyclerView()

        hotelDetailHotelViewModel = ViewModelProvider(this, detailHotelViewModelFactory).get(DetailHotelViewModel::class.java)

        hotelDetailHotelViewModel.getDetailHotel(payload)
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
                }
            }
        }
    }

    fun setupRecyclerView() {
        hotelFacilitiesAdapter = HotelFacilitiesAdapter()
        binding.gridFacilities.apply {
            adapter = hotelFacilitiesAdapter
            layoutManager = GridLayoutManager(this.context, 3)
        }
    }
}