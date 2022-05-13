package com.example.onlinestoreapp.data.db.userdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    suspend fun insertUserData(userData: UserEntity)

    @Query("select * from user_profile_entity")
    fun getUserData() : List<UserEntity>
}