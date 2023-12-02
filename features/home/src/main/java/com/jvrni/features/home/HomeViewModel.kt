package com.jvrni.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jvrni.core.domain.GetUsers
import com.jvrni.core.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getUsers: GetUsers) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState(data = emptyFlow()))
    val state: StateFlow<HomeState> = _state

    init {
        _state.update { it.copy(data = getUsers.invoke().cachedIn(viewModelScope)) }
    }
}

data class HomeState(val data: Flow<PagingData<User>>)
