package com.example.final_project.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.adapters.TypeBasedTourismAdapter
import com.example.final_project.databinding.ActivityTypeBasedTourismBinding
import com.example.final_project.models.DetailTourism
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource
import com.example.final_project.viewmodel.TypeBasedTourismViewModel
import com.example.final_project.viewmodel.TypeBasedTourismViewModelFactory

class TypeBasedTourismActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTypeBasedTourismBinding
    private lateinit var typeBasedTourismViewModel: TypeBasedTourismViewModel

//    private var detailTourism: ArrayList<DetailTourism> = arrayListOf()
    private lateinit var typeBasedTourismAdapter: TypeBasedTourismAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeBasedTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val payload = intent.getStringExtra("tourismType")

        Log.d("INTENT PAYLOAD", payload.toString())

        val repository = Repository()
        val typeBasedTourismViewModelFactory = TypeBasedTourismViewModelFactory(repository, application, payload.toString())

        typeBasedTourismViewModel = ViewModelProvider(this, typeBasedTourismViewModelFactory).get(TypeBasedTourismViewModel::class.java)
        typeBasedTourismViewModel.getTourismByType(payload.toString())
        setupRecyclerView()

        typeBasedTourismViewModel._tourismLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { tourismResponse ->
                        typeBasedTourismAdapter.differ.submitList(tourismResponse.detailTourism)
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
    }

    private fun setupRecyclerView() {
        typeBasedTourismAdapter = TypeBasedTourismAdapter()
        binding.rvTypeBasedTourism.apply {
            adapter = typeBasedTourismAdapter
            layoutManager = GridLayoutManager(this.context, 2)
        }
    }
}