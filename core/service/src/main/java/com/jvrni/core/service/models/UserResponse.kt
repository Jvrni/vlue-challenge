package com.jvrni.core.service.models

import com.google.gson.annotations.SerializedName
import com.jvrni.core.domain.models.User

data class DataResponse(
    @SerializedName("results") val users: List<UserResponse>
)

data class UserResponse(
    @SerializedName("name") val name: NameResponse,
    @SerializedName("email") val email: String,
    @SerializedName("picture") val picture: PictureResponse,
    @SerializedName("phone") val phone: String,
    @SerializedName("registered") val registered: RegisteredResponse,
    @SerializedName("location") val location: LocationResponse
) {
    fun map() = User(
        name = this.name.first,
        lastName = this.name.last,
        email = this.email,
        picture = this.picture.large,
        phone = this.phone,
        registeredDate = this.registered.date,
        location = this.location.map()
    )
}
