package com.example.final_project.ui.activities.listHotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project.adapters.HotelListAdapter
import com.example.final_project.databinding.ActivityListHotelBinding
import com.example.final_project.models.hotel.HotelRequestBody
import com.example.final_project.models.hotel.RecommendationBody
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource

class ListHotelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListHotelBinding
    private lateinit var listHotelViewModel: ListHotelViewModel
    private lateinit var hotelListAdapter: HotelListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destination = intent.getStringExtra("DESTINATION")
        val rating = intent.getStringExtra("RATING")
        val facilities = intent.getStringArrayListExtra("FACILITIES")

        val recommendationBody = RecommendationBody(facilities!!, rating?.toDouble()!!)

        val repository = Repository()
        val listHotelViewModelFactory = ListHotelViewModelFactory(repository, application)

        setupRecyclerView()

        listHotelViewModel = ViewModelProvider(this, listHotelViewModelFactory).get(ListHotelViewModel::class.java)
        fetchDataCluster(destination!!, recommendationBody)
    }

    fun fetchDataCluster(destination: String, recommendationBody: RecommendationBody) {
        listHotelViewModel.getCluster(recommendationBody)
        listHotelViewModel._clusterLiveData.observe(this) {response ->
            when (response) {
                is Resource.Error -> {
                    Toast.makeText(this, "Gagal mendapatkan data cluster", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val cluster = response.data?.cluster
                    fetchDataHotel(destination, cluster!!)
                }
            }
        }
    }

    fun fetchDataHotel(destination: String, cluster: Long) {
        listHotelViewModel.getHotel(destination, cluster)
        listHotelViewModel._hotelLiveData.observe(this) { response ->
            when (response) {
                is Resource.Error -> {
                    Toast.makeText(this, "Gagal mendapatkan data hotel", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    hotelListAdapter.differ.submitList(response.data?.data)
                }
            }
        }
    }

    fun setupRecyclerView() {
        hotelListAdapter = HotelListAdapter()
        binding.rvFavoriteHotel.apply {
            adapter = hotelListAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}