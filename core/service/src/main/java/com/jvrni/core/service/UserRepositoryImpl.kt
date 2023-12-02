package com.jvrni.core.service

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jvrni.core.domain.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val service: Service) : UserRepository {
    override fun getUsers() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            PagingSource(service = service)
        }
    ).flow

    companion object {
        const val PAGE_SIZE = 10
    }
}