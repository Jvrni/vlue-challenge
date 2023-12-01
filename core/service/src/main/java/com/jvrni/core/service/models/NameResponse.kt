package com.jvrni.core.service.models

import com.google.gson.annotations.SerializedName

data class NameResponse(
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)
