package com.example.final_project.models

import com.google.gson.annotations.SerializedName

data class ProfileBody(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("role")
    val role: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("location")
    val location: String? = null
)
