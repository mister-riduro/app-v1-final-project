package com.example.final_project.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OneRoutes(
    val routesID: Long,
    val createdAt: String,
    val updatedAt: String? = null,
    val routesDesc: String,
    val position: Long,
    val tourismID: Long
) : Parcelable
