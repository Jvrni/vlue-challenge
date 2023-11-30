package com.jvrni.core.service.models

import com.jvrni.core.domain.models.User

data class UserResponse(
    val name: NameResponse,
    val email: String,
    val picture: PictureResponse,
    val phone: String,
    val registered: RegisteredResponse,
    val location: LocationResponse
) {
    fun map() = User(
        name = this.name.first,
        lastName = this.name.last,
        email = this.email,
        picture = this.picture.medium,
        phone = this.phone,
        registeredDate = this.registered.date,
        location = this.location.map()
    )
}
