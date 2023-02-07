package com.example.final_project.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTourism(
    val tourismID: Long,
    val createdAt: String,
    val updatedAt: String? = null,
    val tourismName: String,
    val tourismAddress: String,
    val tourismImage: String,
    val tourismType: String,
    val tourismCity: String,
    val provinceName: String,
    val openHour: String,
    val closeHour: String,
    val tourismDescription: String,
    val entryPrice: Long,
    val travelingTime: String,
    val roadCondition: String,
    val tourismRating: Double,
    val latitude: Long,
    val longitude: Long,
    val favoriteID: Long? = null,
    val facilities: List<Long?>,
    val routes: List<Long?>
) : Parcelable
