package com.example.onlinestoreapp.domain.models

data class Product(
    val productId: String? = null,
    val productName: String,
    val productDescription: String = "",
    val productImage: String? = null
)