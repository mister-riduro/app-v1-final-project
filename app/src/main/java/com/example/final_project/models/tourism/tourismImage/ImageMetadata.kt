package com.example.final_project.models.tourism.tourismImage


import com.google.gson.annotations.SerializedName

data class ImageMetadata(
    @SerializedName("exif")
    val exif: ImageExif,
    @SerializedName("ifd0")
    val imageIfd0: ImageIfd0
)