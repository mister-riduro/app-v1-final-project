package com.example.final_project.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HotelFacilities(
    val hfacilitiesId: Long,
    val createdAt: String,
    val updatedAt: String? = null,
    val facilitiesName: String,
    val facilitiesImage: String
) : Parcelable
