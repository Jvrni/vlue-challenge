package com.jvrni.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val number: Int,
    val street: String,
    val city: String,
    val state: String,
    val country: String
): Parcelable
