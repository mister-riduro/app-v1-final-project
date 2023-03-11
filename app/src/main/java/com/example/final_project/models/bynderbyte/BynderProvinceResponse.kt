package com.example.final_project.models.bynderbyte

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class BynderProvinceResponse(
    @SerializedName("code")
    val code: String,

    @SerializedName("messages")
    val messages: String,

    @SerializedName("value")
    val value: List<BynderProvince>
)
