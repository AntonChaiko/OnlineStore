package com.example.onlinestoreapp.data.db.userdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_profile_entity")
data class UserEntity(
    @PrimaryKey
    val userUid:String,
    var email:String,
    var firstName:String,
    var secondName:String,
    var number:String,
    var image:String,

)
