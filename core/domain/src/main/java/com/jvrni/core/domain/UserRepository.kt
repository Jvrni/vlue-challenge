package com.jvrni.core.domain

import androidx.paging.PagingData
import com.jvrni.core.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<PagingData<User>>
}