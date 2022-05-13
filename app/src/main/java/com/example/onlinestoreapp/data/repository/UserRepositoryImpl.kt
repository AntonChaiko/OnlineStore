package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.domain.models.UserProfile
import com.example.onlinestoreapp.domain.repository.UserRepository
import com.example.onlinestoreapp.utils.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : UserRepository {

    override suspend fun setupUserData(userProfile: UserProfile, userUid:String): Task<Void> {
        return firebaseFirestore.collection(Constants.USERS_TABLE_NAME)
            .document(userUid)
            .set(userProfile)
    }

    override suspend fun getUserData(userUid:String): Task<DocumentSnapshot> {
        return firebaseFirestore.collection(Constants.USERS_TABLE_NAME)
            .document(userUid)
            .get()
    }


    override fun getUserUid(): String? = firebaseAuth.currentUser?.uid


}