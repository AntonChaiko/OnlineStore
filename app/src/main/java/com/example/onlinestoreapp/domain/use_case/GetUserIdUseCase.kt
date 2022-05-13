package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.repository.UserRepository
import com.example.onlinestoreapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<Resource<String>> = flow {
        if (userRepository.getUserUid() != null) {
            emit(Resource.Success(userRepository.getUserUid().toString()))
        } else {
            emit(Resource.Error("Something goes wrong"))
        }

    }
}