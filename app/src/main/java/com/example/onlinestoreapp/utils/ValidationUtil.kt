package com.example.onlinestoreapp.utils

import android.util.Patterns

object ValidationUtil {

    private fun isNullOrEmpty(input: String?): Boolean = input == null || input.isEmpty()

/*    fun isValidUsername(username: String?): Resource<String> {
        return when {
            isNullOrEmpty(username?.trim()) -> Resource.Error("Username cannot be empty")
            else -> Resource.Success(username!!)
        }
    }*/

    fun isValidEmail(email: String?): Resource<String> {
        return when {
            isNullOrEmpty(email?.trim()) -> Resource.Error("Email cannot be empty")
            !Patterns.EMAIL_ADDRESS.matcher(email?.trim() as CharSequence).matches() -> Resource.Error("Check your email")
            else -> Resource.Success(email)
        }
    }


    fun isValidPassword(password: String?): Resource<String> {
        return when {
            isNullOrEmpty(password) -> Resource.Error("Password cannot be empty")
            password!!.trim().length < 6 -> Resource.Error("Too short password")
            password.trim().length > 30 -> Resource.Error("Too long password")
            else -> Resource.Success(password)
        }
    }
}