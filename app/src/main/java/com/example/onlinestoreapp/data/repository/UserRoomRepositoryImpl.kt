package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.data.db.userdb.UserDao
import com.example.onlinestoreapp.data.db.userdb.UserEntity
import com.example.onlinestoreapp.domain.repository.UserRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRoomRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) {

     suspend fun updateUserData() {
         withContext(Dispatchers.IO){
             userDao.insertUserData(
                 UserEntity(
                     "ASD", "asd", "ASdas", "sadas", "dasdas", "Sdad"
                 )
             )
         }

    }


}