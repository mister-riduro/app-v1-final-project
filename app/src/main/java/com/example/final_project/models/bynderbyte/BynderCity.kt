package com.example.final_project.models.bynderbyte

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class BynderCity(
    @SerializedName("id")
    val id: String,

    @SerializedName("id_provinsi")
    val id_provinsi: String,

    @SerializedName("name")
    val name: String
)
