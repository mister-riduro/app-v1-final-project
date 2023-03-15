package com.example.final_project.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class Routes(
    @SerializedName("routes_id")
    val routesID: Long,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("routes_desc")
    val routesDesc: String,

    @SerializedName("position")
    val position: Long,

    @SerializedName("tourism_id")
    val tourismID: Long
)
