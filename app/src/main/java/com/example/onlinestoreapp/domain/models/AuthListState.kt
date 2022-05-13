package com.example.onlinestoreapp.domain.models

data class AuthListState(
    val isLoading:Boolean = false,
    val error:String = "",
    val isRegistrationComplete:Boolean = false
)