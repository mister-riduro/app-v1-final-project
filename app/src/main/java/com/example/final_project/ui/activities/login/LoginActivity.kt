package com.example.final_project.ui.activities.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.MainActivity
import com.example.final_project.R
import com.example.final_project.databinding.ActivityLoginBinding
import com.example.final_project.databinding.FragmentBerandaBinding
import com.example.final_project.models.login.LoginBody
import com.example.final_project.models.login.LoginResponse
import com.example.final_project.remote.preferences.Preferences
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.register.RegisterActivity
import com.example.final_project.ui.fragments.BerandaFragment
import com.example.final_project.util.Resource

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val repository = Repository()
        val loginViewModelFactory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)

        binding.btnNext.setOnClickListener {
            setLogin(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }

    }

    fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun setLogin(email: String, password: String) {
        val loginBody = LoginBody(email, password)

        loginViewModel.loginAccount(loginBody)
        loginViewModel._loginLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    saveToken(response.data)
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {

                }
            }
        })
    }

    fun saveToken(data: LoginResponse?) {
        if (!data?.data?.accessToken.isNullOrEmpty()) {
            data?.data?.accessToken.let {
                Preferences.setToken(this, it.toString())
            }

            navigateToHome()
        }
    }
}