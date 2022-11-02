package com.example.final_project.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TourismFacilities(
    val facilities_name: String,
) : Parcelable
