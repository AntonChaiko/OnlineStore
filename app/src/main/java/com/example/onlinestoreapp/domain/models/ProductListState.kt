package com.example.onlinestoreapp.domain.models

data class ProductListState(
    val isLoading:Boolean = false,
    val error:String = "",
    val isProductAdded:Boolean = false
)
