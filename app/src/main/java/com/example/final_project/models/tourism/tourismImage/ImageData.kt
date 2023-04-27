package com.example.final_project.models.tourism.tourismImage


import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("charset")
    val charset: Any,
    @SerializedName("description")
    val description: Any,
    @SerializedName("duration")
    val duration: Any,
    @SerializedName("embed")
    val embed: Any,
    @SerializedName("filename_disk")
    val filenameDisk: String,
    @SerializedName("filename_download")
    val filenameDownload: String,
    @SerializedName("filesize")
    val filesize: String,
    @SerializedName("folder")
    val folder: Any,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("location")
    val location: Any,
    @SerializedName("metadata")
    val imageMetadata: ImageMetadata,
    @SerializedName("modified_by")
    val modifiedBy: String,
    @SerializedName("modified_on")
    val modifiedOn: String,
    @SerializedName("storage")
    val storage: String,
    @SerializedName("tags")
    val tags: Any,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uploaded_by")
    val uploadedBy: String,
    @SerializedName("uploaded_on")
    val uploadedOn: String,
    @SerializedName("width")
    val width: Int
)