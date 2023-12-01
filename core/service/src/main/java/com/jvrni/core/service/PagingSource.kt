package com.jvrni.core.service

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jvrni.core.domain.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PagingSource(private val service: Service) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return withContext(Dispatchers.IO) {
            try {
                val result = service.getUsers(page = params.key ?: STARTING_PAGE_INDEX)
                val list = result.users.map { response -> response.map() }

                LoadResult.Page(
                    data = list,
                    prevKey = null,
                    nextKey = if (result.users.isNotEmpty()) params.key?.plus(1)
                        ?: STARTING_PAGE_INDEX.plus(1) else null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}