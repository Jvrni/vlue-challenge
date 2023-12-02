package com.jvrni.core.service

import com.jvrni.core.service.models.DataResponse
import com.jvrni.core.service.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") quantity: Int = 10
    ): DataResponse
}