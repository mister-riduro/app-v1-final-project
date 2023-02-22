package com.example.final_project.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTourism(
    val favoriteID: Long,
    val createdAt: String,
    val updatedAt: String? = null,
    val userID: String,
    val tourisms: List<DetailTourism>
) : Parcelable
