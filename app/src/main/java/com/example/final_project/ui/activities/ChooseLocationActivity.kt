package com.example.final_project.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.final_project.databinding.ActivityChooseLocationBinding

class ChooseLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}