package com.example.onlinestoreapp.domain.repository

interface UserRoomRepository {

    fun getUserData()
    suspend fun  updateUserData()
}