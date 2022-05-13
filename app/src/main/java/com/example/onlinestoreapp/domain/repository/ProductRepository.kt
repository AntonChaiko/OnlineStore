package com.example.onlinestoreapp.domain.repository

import com.example.onlinestoreapp.domain.models.Product
import com.google.android.gms.tasks.Task

interface ProductRepository {
   suspend fun addProduct(product:Product) : Task<Void>
    fun getProduct(productId: String) : Product
    fun removeProduct(productId: String)
    fun getAllProducts(): List<Product>

}