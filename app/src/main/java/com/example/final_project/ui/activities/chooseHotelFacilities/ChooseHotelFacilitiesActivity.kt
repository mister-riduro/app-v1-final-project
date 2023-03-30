package com.example.final_project.ui.activities.chooseHotelFacilities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.adapters.HotelFacilitiesSelectorAdapter
import com.example.final_project.adapters.TypeBasedTourismAdapter
import com.example.final_project.databinding.ActivityChooseHotelFacilitiesBinding
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.listHotel.ListHotelActivity
import com.example.final_project.ui.activities.typeBasedTourism.TypeBasedTourismViewModelFactory
import com.example.final_project.util.Resource

class ChooseHotelFacilitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseHotelFacilitiesBinding
    private lateinit var chooseHotelFacilitiesViewModel: ChooseHotelFacilitiesViewModel
    private lateinit var hotelFacilitiesSelectorAdapter: HotelFacilitiesSelectorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseHotelFacilitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hotelDestination = intent.getStringExtra("HOTEL_DEST")
        val hotelRating = intent.getDoubleExtra("HOTEL_RATING", 0.0)

        Log.d("RATING DI FACIL", "$hotelRating")

        val repository = Repository()
        val chooseHotelFacilitiesViewModelFactory = ChooseHotelFacilitiesViewModelFactory(repository, application)

        chooseHotelFacilitiesViewModel = ViewModelProvider(this, chooseHotelFacilitiesViewModelFactory).get(ChooseHotelFacilitiesViewModel::class.java)
        chooseHotelFacilitiesViewModel.getHotelFacilities()

        setupRecyclerView()

        chooseHotelFacilitiesViewModel._hotelFacilitiesLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { facilitiesResponse ->
                        hotelFacilitiesSelectorAdapter.differ.submitList(facilitiesResponse.data)

                        val selectedData = hotelFacilitiesSelectorAdapter.getSelectedItems()

                        binding.btnSearch.setOnClickListener {
                            val intent = Intent(this, ListHotelActivity::class.java)
                            intent.putExtra("DESTINATION", hotelDestination)
                            intent.putExtra("RATING", hotelRating)
                            intent.putStringArrayListExtra("FACILITIES", selectedData)
                            startActivity(intent)
                        }
                    }
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    response.message.let { message ->
                        Log.d("ERROR FACILITIES", "Error occured : $message")
                    }
                }
            }
        })

    }

    private fun setupRecyclerView() {
        hotelFacilitiesSelectorAdapter = HotelFacilitiesSelectorAdapter()
        binding.rvFacilities.apply {
            adapter = hotelFacilitiesSelectorAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}