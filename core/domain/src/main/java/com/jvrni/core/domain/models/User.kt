package com.jvrni.core.domain.models

data class User(
    val name: String,
    val lastName: String,
    val email: String,
    val picture: String,
    val phone: String,
    val registeredDate: String,
    val location: Location
)
