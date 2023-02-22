package com.example.final_project.models

data class NearestEvent(
    val nearestEventID: Int,
    val eventImage: String,
    val eventName: String,
    val eventStartDate: String,
    val eventEndDate: String,
    val eventLocation: String,
    val eventDescription: String,
)
