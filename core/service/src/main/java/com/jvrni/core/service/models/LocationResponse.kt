package com.jvrni.core.service.models

import com.jvrni.core.domain.models.Location

data class LocationResponse(
    val street: StreetResponse,
    val city: String,
    val state: String,
    val country: String
) {
    fun map() = Location(
        number = this.street.number,
        street = this.street.name,
        city = this.city,
        state = this.state,
        country = this.country
    )
}
