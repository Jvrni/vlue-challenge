package com.jvrni.core.service

import com.jvrni.core.domain.UserRepository
import com.jvrni.core.domain.models.User
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: Service
) : UserRepository {
    override fun getUsers() = flow<List<User>> {
        emit(service.getUsers().map { it.map() })
    }
}