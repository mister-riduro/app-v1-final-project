package com.example.final_project.ui.activities.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.MainActivity
import com.example.final_project.databinding.ActivityRegisterBinding
import com.example.final_project.models.profiles.ProfileBody
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.chooseLocation.ChooseLocationActivity
import com.example.final_project.ui.activities.login.LoginActivity
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

        supportActionBar?.hide()

        val repository = Repository()
        val registerViewModelFactory = RegisterViewModelFactory(repository, application)
        val userRole = "af80f2d4-425f-4c3a-8f5f-f817cdebfb01"

        registerViewModel = ViewModelProvider(this, registerViewModelFactory).get(
            RegisterViewModel::class.java
        )

        checkSession()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnNext.setOnClickListener {
            email = binding.etEmail.text.toString()
            password = binding.etPassword.text.toString()
            firstName = binding.etFirstName.text.toString()
            lastName = binding.etLastName.text.toString()

            val profile = ProfileBody(email, password, userRole, firstName, lastName)
            registerViewModel.createUser(profile)

            registerViewModel._userLiveData.observe(this, Observer {
                when(it) {
                    is Resource.Success -> {
                        it.data?.let { profileResponse ->
                            Log.d("SUCCESS", "Success Create User on DB")
                            val intent = Intent(this, ChooseLocationActivity::class.java)
                            intent.putExtra("USERID", profileResponse.data.id)
                            startActivity(intent)
                        }
                    }
                    is Resource.Loading -> {
                        Log.d("LOADING GETTING DATA", "Loading data")
                    }
                    is Resource.Error -> {
                        it.message.let { message ->
                            Log.d("ERROR GETTING DATA", "Error occured : $message")
                        }
                    }
                }
            })
        }
    }
    fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun checkSession() {
        if (registerViewModel.isLoggedIn()) {
            if (registerViewModel.isExpired()) {
                registerViewModel.resetPref()
                Toast.makeText(this, "Sesi sudah berakhir", Toast.LENGTH_SHORT).show()
            } else {
                navigateToHome()
            }
        }
    }
}