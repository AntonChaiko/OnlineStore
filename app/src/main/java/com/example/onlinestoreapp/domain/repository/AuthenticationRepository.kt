package com.example.onlinestoreapp.domain.repository

import com.example.onlinestoreapp.domain.models.UserCredentials
import com.example.onlinestoreapp.utils.Result
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthenticationRepository {
    suspend fun logIn(userCredentials: UserCredentials) : Task<AuthResult>
    suspend fun logOut()
    suspend fun registerUser(userCredentials: UserCredentials): Task<AuthResult>
    suspend fun currentUser(): Boolean
}