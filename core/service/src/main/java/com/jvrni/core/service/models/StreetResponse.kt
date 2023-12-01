package com.jvrni.core.service.models

import com.google.gson.annotations.SerializedName

data class StreetResponse(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
)
