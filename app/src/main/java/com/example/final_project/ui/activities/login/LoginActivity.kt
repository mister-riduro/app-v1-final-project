package com.example.final_project.ui.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.auth0.android.jwt.JWT
import com.example.final_project.MainActivity
import com.example.final_project.databinding.ActivityLoginBinding
import com.example.final_project.models.login.LoginBody
import com.example.final_project.models.login.LoginResponse
import com.example.final_project.remote.preferences.Preferences
import com.example.final_project.remote.repository.Repository
import com.example.final_project.ui.activities.register.RegisterActivity
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

    fun setLogin(email: String, password: String) {
        val loginBody = LoginBody(email, password)

        loginViewModel.loginAccount(loginBody)
        loginViewModel._loginLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    saveToken(response.data)
                    navigateToHome()
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    Toast.makeText(this, "Email / Password Salah", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun saveToken(data: LoginResponse?) {
        if (!data?.data?.accessToken.isNullOrEmpty()) {
            data?.data?.let {
                val jwt = JWT(it.accessToken)
                val userID = jwt.getClaim("id").asString()

                loginViewModel.setValues(it.accessToken, it.expires.toInt(), true, userID.toString())
            }
        }
    }
}