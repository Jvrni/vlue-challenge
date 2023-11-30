package com.jvrni.core.domain

import com.jvrni.core.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>
}