package com.example.final_project.ui.activities.register

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.databinding.ActivityRegisterBinding
import com.example.final_project.models.Profile
import com.example.final_project.models.ProfileBody
import com.example.final_project.remote.repository.Repository
import com.example.final_project.util.Resource

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val registerViewModelFactory = RegisterViewModelFactory(repository, application)
        val userRole = "af80f2d4-425f-4c3a-8f5f-f817cdebfb01"

        registerViewModel = ViewModelProvider(this, registerViewModelFactory).get(
            RegisterViewModel::class.java
        )

        binding.btnNext.setOnClickListener {
            email = binding.etEmail.text.toString()
            password = binding.etPassword.text.toString()
            firstName = binding.etFirstName.text.toString()
            lastName = binding.etLastName.text.toString()

            Log.d("EMAIL :", email)

            val profile = ProfileBody(email, password, userRole, firstName, lastName, "")

            registerViewModel.createUser(profile)

            registerViewModel._userLiveData.observe(this, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        response.data?.let { response ->
                            Log.d("SUCCESS", "Success Create User on DB")
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
}