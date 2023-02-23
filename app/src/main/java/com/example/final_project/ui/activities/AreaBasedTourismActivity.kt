package com.example.final_project.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
//import com.example.final_project.adapters.AreaBasedTourismAdapter
import com.example.final_project.databinding.ActivityAreaBasedTourismBinding
import com.example.final_project.models.DetailTourism
import com.example.final_project.models.`object`.DetailTourismObjects

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

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
//        detailTourism.addAll(DetailTourismObjects.detail_tourism_objects)
//        binding.rvAreaBasedTourism.layoutManager = layoutManager
//        areaBasedTourismAdapter = AreaBasedTourismAdapter(detailTourism)
//        binding.rvAreaBasedTourism.adapter = areaBasedTourismAdapter


        val area = intent.getStringExtra(EXTRA_NAME).toString()
        supportActionBar?.title = "Wisata $area"
        supportActionBar?.setIcon(R.drawable.icon_arrow_left)
    }
}