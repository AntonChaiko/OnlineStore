package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.models.UserProfile
import com.example.onlinestoreapp.domain.repository.UserRepository
import com.example.onlinestoreapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(
        userId: String
    ): Flow<Resource<UserProfile>> = flow {
        var result: Resource<UserProfile> = Resource.Error("")

        try {
            userRepository.getUserData(userId).addOnCompleteListener { task ->
                val data = task.result
                result = if (data != null) {
                    Resource.Success(data.toObject(UserProfile::class.java)!!)
                } else {
                    Resource.Error(task.exception?.localizedMessage ?: "An unexpected error")
                }

            }.await()

            emit(result)

        } catch (ex: Exception) {
            emit(Resource.Error(ex.localizedMessage ?: "An unexpected error"))
        }

    }

}