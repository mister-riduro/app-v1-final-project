package com.example.final_project.models.login

import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)
