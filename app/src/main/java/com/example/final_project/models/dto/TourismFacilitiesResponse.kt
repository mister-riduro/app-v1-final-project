package com.example.final_project.models.dto

import android.os.Parcelable
import com.example.final_project.models.TourismFacilities
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TourismFacilitiesResponse(
    val id: Long,
    val tourismsTourismId: Long,
    val tfacilitiesTfacilitiesId: TourismFacilities
) : Parcelable
