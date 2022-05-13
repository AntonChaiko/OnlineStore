package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend fun invoke() {
            if (authenticationRepository.currentUser()) authenticationRepository.logOut()
    }
}