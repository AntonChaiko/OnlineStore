package com.example.onlinestoreapp.utils

sealed class Result {
    data class Success(val data: String) : Result()
    data class Failure(val data: String) : Result()
}

