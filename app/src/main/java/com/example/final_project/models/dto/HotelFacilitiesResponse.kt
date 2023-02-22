package com.example.final_project.models.dto

import android.os.Parcelable
import com.example.final_project.models.HotelFacilities
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HotelFacilitiesResponse(
    val id: Long,
    val hotelsHotelId: Long,
    val hfacilitiesHfacilitiesId: HotelFacilities
) : Parcelable
