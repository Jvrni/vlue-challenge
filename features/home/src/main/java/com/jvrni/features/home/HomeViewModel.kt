package com.jvrni.features.home

import androidx.lifecycle.ViewModel
import com.jvrni.core.domain.GetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getUsers: GetUsers) : ViewModel() {

}