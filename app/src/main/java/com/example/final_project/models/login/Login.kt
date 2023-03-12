package com.example.final_project.models.login

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("expires")
    val expires: Long,

    @SerializedName("refresh_token")
    val refreshToken: String
)
