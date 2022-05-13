package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.models.UserProfile
import com.example.onlinestoreapp.domain.repository.UserRepository
import com.example.onlinestoreapp.utils.Resource
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class UpdateUserDataUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userProfile: UserProfile): Flow<Resource<String>> = flow {

        var result: Resource<String> = Resource.Error("")

        try {
            emit(Resource.Loading())
            
            repository.setupUserData(userProfile, repository.getUserUid() ?: "Error")
                .addOnCompleteListener { task ->
                    result = if (task.isSuccessful) {
                        Resource.Success("Success")
                    } else {
                        Resource.Error("An unexpected error")
                    }
                }.await()
            emit(result)

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        }
    }
}