package com.example.final_project.ui.activities.areaBasedTourism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.adapters.AreaBasedTourismAdapter
//import com.example.final_project.adapters.AreaBasedTourismAdapter
import com.example.final_project.databinding.ActivityAreaBasedTourismBinding
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource

class AreaBasedTourismActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAreaBasedTourismBinding
    private lateinit var areaBasedTourismAdapter: AreaBasedTourismAdapter
    private lateinit var areaBasedTourismViewModel: AreaBasedTourismViewModel
    companion object {
        const val EXTRA_NAME = "provinceName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAreaBasedTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val provinceName = intent.getStringExtra(EXTRA_NAME).toString()

        val repository = Repository()
        val areaBasedTourismViewModelFactory = AreaBasedTourismViewModelFactory(application, repository)

        areaBasedTourismViewModel = ViewModelProvider(this, areaBasedTourismViewModelFactory).get()
        areaBasedTourismViewModel.getTourismByProvince(provinceName)

        setupRecyclerView()

        areaBasedTourismViewModel._tourismLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { tourismResponse ->
                        areaBasedTourismAdapter.differ.submitList(tourismResponse.data)
                    }
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    response.message.let { message ->
                        Log.d("ERROR TOURISM", "Error occured : $message")
                    }
                }
                else -> {

                }
            }
        })

        supportActionBar?.title = "Wisata $provinceName"
        supportActionBar?.setIcon(R.drawable.icon_arrow_left)
    }

    private fun setupRecyclerView() {
        areaBasedTourismAdapter = AreaBasedTourismAdapter()
        binding.rvAreaBasedTourism.apply {
            adapter = areaBasedTourismAdapter
            layoutManager = GridLayoutManager(this.context, 2)
        }
    }
}