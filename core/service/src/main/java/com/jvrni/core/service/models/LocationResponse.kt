package com.jvrni.core.service.models

import com.google.gson.annotations.SerializedName
import com.jvrni.core.domain.models.Location

data class LocationResponse(
    @SerializedName("street") val street: StreetResponse,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String
) {
    fun map() = Location(
        number = this.street.number,
        street = this.street.name,
        city = this.city,
        state = this.state,
        country = this.country
    )
}
