package com.jvrni.core.domain

import javax.inject.Inject

class GetUsers @Inject constructor(private val repository: UserRepository) {

    fun invoke() = repository.getUsers()
}