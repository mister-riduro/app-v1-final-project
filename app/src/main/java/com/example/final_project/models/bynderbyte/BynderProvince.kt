package com.example.final_project.models.bynderbyte

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class BynderProvince(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String
)
