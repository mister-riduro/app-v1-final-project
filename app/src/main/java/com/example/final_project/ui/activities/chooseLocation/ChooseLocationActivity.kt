package com.example.final_project.ui.activities.chooseLocation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.R
import com.example.final_project.databinding.ActivityChooseLocationBinding
import com.example.final_project.models.profiles.ProfileLocation
import com.example.final_project.models.bynderbyte.BynderCity
import com.example.final_project.models.bynderbyte.BynderProvince
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.login.LoginActivity
import com.example.final_project.util.Resource

class ChooseLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseLocationBinding
    private lateinit var chooseLocationViewModel: ChooseLocationViewModel

    private var cityName: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val payload = intent.getStringExtra("USERID")
        val userID = payload.toString()

        Log.d("PAYLOAD", userID)

        val repository = Repository()
        val chooseLocationViewModelFactory = ChooseLocationViewModelFactory(repository, application)

        chooseLocationViewModel = ViewModelProvider(this, chooseLocationViewModelFactory).get(ChooseLocationViewModel::class.java)

        setupChooseProvince()

        binding.inputProvinsi.setOnItemClickListener { adapterView, view, position, l ->
            val adapter = binding.inputProvinsi.adapter
            val itemProvince = adapter.getItem(position) as BynderProvince
            setupChooseCity(itemProvince.id)
        }

        binding.inputKota.setOnItemClickListener { adapterView, view, position, l ->
            val adapter = binding.inputKota.adapter
            val itemCity = adapter.getItem(position) as BynderCity
            cityName = itemCity.name
        }

        binding.btnFinishRegis.setOnClickListener {
            val profileLocation = ProfileLocation(cityName)
            chooseLocationViewModel.updateLocation(userID, profileLocation)
            chooseLocationViewModel._userLocationLiveData.observe(this, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        response.data?.let { response ->
                            Log.d("SUCCESS", "Success Update User Location")
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
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
    }


    fun setupChooseCity(itemProvince: String) {
        chooseLocationViewModel.getCities(itemProvince)
        chooseLocationViewModel._cityLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { response ->
                        Log.d("SUCCESS", "Success Get Cities")
                        val adapter = ArrayAdapter(
                            this,
                            R.layout.item_location,
                            ArrayList(response.value.sortedBy { it.name })
                        )
                        binding.inputKota.setAdapter(adapter)
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

    fun setupChooseProvince() {
        chooseLocationViewModel.getProvinces()
        chooseLocationViewModel._provinceLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { response ->
                        Log.d("SUCCESS", "Success Get Provinces")
                        val adapter = ArrayAdapter(
                            this,
                            R.layout.item_location,
                            ArrayList(response.value.sortedBy { it.name })
                        )
                        binding.inputProvinsi.setAdapter(adapter)
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
}
