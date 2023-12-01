package com.jvrni.core.service.models

import com.google.gson.annotations.SerializedName

data class PictureResponse(
    @SerializedName("medium") val medium: String
)
