package com.example.final_project.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.databinding.ActivityTourismDetailBinding

class TourismDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTourismDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTourismDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }
    }
}