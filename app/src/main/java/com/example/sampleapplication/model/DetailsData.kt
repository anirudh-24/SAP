package com.example.sampleapplication.model

import com.google.gson.annotations.SerializedName

data class DetailsData(
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("city")
    val city: String? = "",
)