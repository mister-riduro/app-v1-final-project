package com.example.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.final_project.databinding.ActivityMainBinding
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.fragments.wisata.WisataViewModel
import com.example.final_project.ui.fragments.wisata.WisataViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val hostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = hostFragment.navController

        bottomNavigationView.setupWithNavController(navController)
        supportActionBar?.hide()
    }
}