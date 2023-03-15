package com.example.final_project.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class TourismFacilities(
    @SerializedName("tfacilities_id")
    val tfacilitiesID: Long,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("facilities_name")
    val facilitiesName: String
)
