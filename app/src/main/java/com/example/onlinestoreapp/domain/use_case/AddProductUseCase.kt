package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.models.Product
import com.example.onlinestoreapp.domain.repository.ProductRepository
import com.example.onlinestoreapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(product: Product): Flow<Resource<String>> = flow {
        var result: Resource<String> = Resource.Error("Something goes wrong")

        try {
            emit(Resource.Loading())

            repository.addProduct(product).addOnCompleteListener { task ->
                result = if (task.isSuccessful) {
                    Resource.Success("Success")
                } else {
                    Resource.Error(task.exception?.localizedMessage ?: "An unexpected error")
                }
            }.await()
            emit(result)

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        }

    }
}