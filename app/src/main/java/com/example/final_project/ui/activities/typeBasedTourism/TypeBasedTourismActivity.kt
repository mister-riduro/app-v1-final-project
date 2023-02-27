package com.example.final_project.ui.activities.typeBasedTourism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.final_project.R
import com.example.final_project.adapters.TypeBasedTourismAdapter
import com.example.final_project.databinding.ActivityTypeBasedTourismBinding
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource

class TypeBasedTourismActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTypeBasedTourismBinding
    private lateinit var typeBasedTourismViewModel: TypeBasedTourismViewModel
    private lateinit var typeBasedTourismAdapter: TypeBasedTourismAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeBasedTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val payload = intent.getStringExtra("tourismType")
        val repository = Repository()
        val typeBasedTourismViewModelFactory = TypeBasedTourismViewModelFactory(repository, application, payload.toString())

        typeBasedTourismViewModel = ViewModelProvider(this, typeBasedTourismViewModelFactory).get(
            TypeBasedTourismViewModel::class.java)
        typeBasedTourismViewModel.getTourismByType(payload.toString())
        setupRecyclerView()

        typeBasedTourismViewModel._tourismLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { tourismResponse ->
                        typeBasedTourismAdapter.differ.submitList(tourismResponse.data)
                    }
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    response.message.let { message ->
                        Log.d("ERROR TOURISM", "Error occured : $message")
                    }
                }
            }
        })

        supportActionBar?.setIcon(R.drawable.icon_arrow_left)
    }

    private fun setupRecyclerView() {
        typeBasedTourismAdapter = TypeBasedTourismAdapter()
        binding.rvTypeBasedTourism.apply {
            adapter = typeBasedTourismAdapter
            layoutManager = GridLayoutManager(this.context, 2)
        }
    }
}