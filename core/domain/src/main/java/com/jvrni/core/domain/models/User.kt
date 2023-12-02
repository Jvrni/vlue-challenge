package com.jvrni.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val name: String,
    val lastName: String,
    val email: String,
    val picture: String,
    val phone: String,
    val registeredDate: String,
    val location: Location
): Parcelable
