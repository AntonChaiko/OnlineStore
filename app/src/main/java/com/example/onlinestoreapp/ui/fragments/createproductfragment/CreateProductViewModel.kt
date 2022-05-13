package com.example.onlinestoreapp.ui.fragments.createproductfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.models.Product
import com.example.onlinestoreapp.domain.models.ProductListState
import com.example.onlinestoreapp.domain.use_case.AddProductUseCase
import com.example.onlinestoreapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CreateProductViewModel @Inject constructor(
    private val addProduct: AddProductUseCase
) : ViewModel() {

    private val _productListState = MutableLiveData(ProductListState())
    val productListState: LiveData<ProductListState> = _productListState


    val productName: MutableLiveData<String> = MutableLiveData()
    val productDescription: MutableLiveData<String> = MutableLiveData()
    val productPrice: MutableLiveData<String> = MutableLiveData()

    fun addProduct() {

        addProduct.invoke(product = setupProduct()).onEach { resource ->
            when (resource) {

                is Resource.Loading -> _productListState.value = ProductListState(isLoading = true)

                is Resource.Error -> {
                    _productListState.value = ProductListState(isLoading = false)
                    _productListState.value =
                        ProductListState(error = resource.message ?: "Try again")
                }

                is Resource.Success -> {
                    _productListState.value = ProductListState(isProductAdded = true)
                    _productListState.value = ProductListState(isLoading = false)

                }
            }

        }.launchIn(viewModelScope)
    }

    private fun setupProduct(): Product {
        return Product(
            productId = "1",
            productName = productName.value.toString(),
            productDescription = productDescription.value.toString(),
            productImage = "empty",

            )
    }


}