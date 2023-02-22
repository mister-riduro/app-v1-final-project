package com.example.final_project.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.adapters.TypeBasedTourismAdapter
import com.example.final_project.databinding.ActivityTypeBasedTourismBinding
import com.example.final_project.models.DetailTourism
import com.example.final_project.remote.network.NetworkResult
import com.example.final_project.viewmodel.TypeBasedTourismViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeBasedTourismActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTypeBasedTourismBinding
    private val typeBasedTourismViewModel by viewModels<TypeBasedTourismViewModel>()
    private var detailTourism: ArrayList<DetailTourism> = arrayListOf()
    private lateinit var typeBasedTourismAdapter: TypeBasedTourismAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeBasedTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val payload = intent.getStringExtra("tourismType")

        fetchData(payload.toString())
    }

    private fun fetchData(tourismType: String) {
        typeBasedTourismViewModel.fetchTourismResponse(tourismType)
        typeBasedTourismViewModel.tourismLiveData.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
                    response.data?.let { detailTourism.addAll(it) }
                    binding.rvTypeBasedTourism.layoutManager = layoutManager
                    typeBasedTourismAdapter = TypeBasedTourismAdapter(detailTourism)
                    binding.rvTypeBasedTourism.adapter = typeBasedTourismAdapter
                }
                is NetworkResult.Error -> {
                    // show error message
                }
                is NetworkResult.Loading -> {
                    // show a progress bar
                }
            }
        }
    }
}