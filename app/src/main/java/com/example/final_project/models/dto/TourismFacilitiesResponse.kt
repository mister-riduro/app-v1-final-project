package com.example.final_project.models.dto

import android.os.Parcelable
import com.example.final_project.models.TourismFacilities
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TourismFacilitiesResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("tourisms_tourism_id")
    val tourismsTourismId: Long,

    @SerializedName("tfacilities_tfacilities_id")
    val tfacilitiesTfacilitiesId: TourismFacilities
)
