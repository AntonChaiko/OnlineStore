package com.example.onlinestoreapp.utils

sealed class UserResult<T> {
    data class Success<T>(val data: T) : UserResult<T>()
    data class Failure<T>(val data: T) : UserResult<T>()
}
