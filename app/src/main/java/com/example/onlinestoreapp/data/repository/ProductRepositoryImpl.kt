package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.domain.models.Product
import com.example.onlinestoreapp.domain.repository.ProductRepository
import com.example.onlinestoreapp.utils.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : ProductRepository {
   override suspend fun addProduct(product: Product): Task<Void> {
        return firebaseFirestore.collection(Constants.PRODUCTS)
            .document()
            .set(product)

    }

    override fun getProduct(productId: String): Product {
        TODO("Not yet implemented")
    }

    override fun removeProduct(productId: String) {
        TODO("Not yet implemented")
    }

    override fun getAllProducts(): List<Product> {
        TODO("Not yet implemented")
    }
}