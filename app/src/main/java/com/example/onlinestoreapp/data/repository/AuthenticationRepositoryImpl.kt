package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.domain.models.UserCredentials
import com.example.onlinestoreapp.domain.repository.AuthenticationRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) :
    AuthenticationRepository {

    override suspend fun logIn(userCredentials: UserCredentials): Task<AuthResult>  {
        userCredentials.apply {
            return firebaseAuth.signInWithEmailAndPassword(email, password)
        }

    }

    override suspend fun logOut() {
        firebaseAuth.signOut()
    }

    override suspend fun registerUser(userCredentials: UserCredentials): Task<AuthResult> {
        userCredentials.apply {
            return firebaseAuth.createUserWithEmailAndPassword(email, password)
        }
    }

    override suspend fun currentUser(): Boolean {
        return firebaseAuth.currentUser != null
    }



}