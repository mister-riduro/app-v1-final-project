package com.example.final_project.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailHotel(
    val hotelID: Long,
    val createdAt: String,
    val updatedAt: String,
    val propertyType: String,
    val hotelCity: String,
    val provinceName: String,
    val hotelAddress: String,
    val hotelRating: Double,
    val minPrice: Long,
    val maxPrice: Long,
    val latitude: Long,
    val longitude: Long,
    val cluster: Long,
    val hotelImage: String,
    val hotelName: String,
    val facilities: List<HotelFacilities>
) : Parcelable
