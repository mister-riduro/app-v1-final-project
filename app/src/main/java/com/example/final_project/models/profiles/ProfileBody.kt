package com.example.final_project.models.profiles

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
    val lastName: String
)
