package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.models.UserCredentials
import com.example.onlinestoreapp.domain.repository.AuthenticationRepository
import com.example.onlinestoreapp.utils.Resource
import com.example.onlinestoreapp.utils.ValidationUtil
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class LogInUseCase @Inject constructor(
    private val repository: AuthenticationRepository

) {
    operator fun invoke(
        userCredentials: UserCredentials
    ): Flow<Resource<String>> = flow {

        val password = ValidationUtil.isValidPassword(userCredentials.password)
        val email = ValidationUtil.isValidEmail(userCredentials.email)
        var result: Resource<String> = Resource.Error("")

        try {
            when {
                email is Resource.Error -> emit(
                    Resource.Error(
                        email.message ?: "An unexpected error"
                    )
                )
                password is Resource.Error -> emit(
                    Resource.Error(
                        password.message ?: "An unexpected error"
                    )
                )

                else -> {
                    emit(Resource.Loading())
                    repository.logIn(userCredentials).addOnCompleteListener { task ->
                        result = if (task.isSuccessful) {
                            Resource.Success("Success")
                        } else {
                            Resource.Error(
                                "Something goes Wrong"
                            )
                        }
                    }.await()

                    emit(result)
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        }
    }
}
