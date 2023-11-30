package com.jvrni.core.service

import com.jvrni.core.service.models.UserResponse
import retrofit2.http.GET

interface Service {

    @GET()
    suspend fun getUsers(): List<UserResponse>
}