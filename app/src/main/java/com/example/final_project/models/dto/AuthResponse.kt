package com.example.final_project.models.dto

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("expires")
    val expires: Long,

    @SerializedName("refresh_token")
    val refreshToken: String
)
