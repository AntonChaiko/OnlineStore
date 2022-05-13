package com.example.onlinestoreapp.domain.repository

import com.example.onlinestoreapp.domain.models.UserProfile
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface UserRepository {
    suspend fun setupUserData(userProfile: UserProfile, userUid: String): Task<Void>
    suspend fun getUserData(userUid:String):Task<DocumentSnapshot>
    fun getUserUid(): String?
}