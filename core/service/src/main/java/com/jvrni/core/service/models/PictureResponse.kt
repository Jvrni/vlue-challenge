package com.jvrni.core.service.models

import com.google.gson.annotations.SerializedName

data class PictureResponse(
    @SerializedName("large") val large: String
)
