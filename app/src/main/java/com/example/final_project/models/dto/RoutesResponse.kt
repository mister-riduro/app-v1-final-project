package com.example.final_project.models.dto

import android.os.Parcelable
import com.example.final_project.models.Routes
import kotlinx.android.parcel.Parcelize

data class RoutesResponse(
    val routes: List<Routes>
)
