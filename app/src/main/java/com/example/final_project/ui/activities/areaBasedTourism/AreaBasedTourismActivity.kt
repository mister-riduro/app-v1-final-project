package com.example.final_project.ui.activities.areaBasedTourism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
//import com.example.final_project.adapters.AreaBasedTourismAdapter
import com.example.final_project.databinding.ActivityAreaBasedTourismBinding
import com.example.final_project.models.DetailTourism
import com.example.final_project.remote.repository.Repository

class AreaBasedTourismActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAreaBasedTourismBinding
//    private lateinit var areaBasedTourismAdapter: AreaBasedTourismAdapter
    private var detailTourism: ArrayList<DetailTourism> = arrayListOf()

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAreaBasedTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
//        binding.rvAreaBasedTourism.layoutManager = layoutManager
//        areaBasedTourismAdapter = AreaBasedTourismAdapter(detailTourism)
//        binding.rvAreaBasedTourism.adapter = areaBasedTourismAdapter


        val area = intent.getStringExtra(EXTRA_NAME).toString()
        supportActionBar?.title = "Wisata $area"
        supportActionBar?.setIcon(R.drawable.icon_arrow_left)
    }

    private fun setupRecyclerView() {

    }
}