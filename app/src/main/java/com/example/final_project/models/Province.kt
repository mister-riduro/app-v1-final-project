package com.example.final_project.models

import com.google.gson.annotations.SerializedName

data class Province(
    @SerializedName("province_id")
    val provinceID: Int,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("province_name")
    val provinceName: String,

    @SerializedName("province_image")
    val provinceImage: String,
)
