package com.example.final_project.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailTourism(
    val tourism_image: Int,
    val tourism_name: String,
    val tourism_location: String,
    val tourism_rating: Double,
    val tourism_type: String,
    val tourism_open_hour: String,
    val tourism_address: String,
    val tourism_ticket_price: String,
    val tourism_description: String,
    val tourism_facilities: List<TourismFacilities>,
    val tourism_route: String,
    val tourism_travel_time: String,
    val tourism_road_condition: String
) : Parcelable
