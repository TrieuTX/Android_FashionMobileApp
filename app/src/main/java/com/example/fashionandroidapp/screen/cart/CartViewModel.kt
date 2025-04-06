package com.example.fashionandroidapp.screen.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fashionandroidapp.data.model.CartProduct
import com.example.fashionandroidapp.data.model.Product
import com.example.fashionandroidapp.data.repository.CartProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartProductRepository) : ViewModel() {
    val cartProducts = repository.allCartProducts.asLiveData()

    fun checkAndIncreaseProductToCart(productId: Int) {
        viewModelScope.launch {
            val exists = repository.isProductInCart(productId)
            if (exists) {
                increaseQuantity(productId)
            } else {
                addToCart(productId)
            }
        }
    }

    fun checkAndDecreaseProductToCart(productId: Int) {
        viewModelScope.launch {
            val quantity = repository.getQuantityByProductId(productId)
            if (quantity !=  null) {
                if (quantity <= 1) {
                    deleteProduct(productId)
                } else {
                    decreaseQuantity(productId)
                }
            }
        }
    }

    suspend fun getCartProductByProductId(productId: Int): CartProduct? {
        return repository.getCartProductByProductId(productId)
    }

    private fun deleteProduct(productId: Int) {
        viewModelScope.launch {
            getCartProductByProductId(productId)?.let { repository.delete(it) }
        }
    }


    private fun addToCart(productId: Int) {
        viewModelScope.launch {
            repository.insert(CartProduct(productId = productId, quantity = 1))
        }
    }

    private fun increaseQuantity(productId: Int) {
        viewModelScope.launch {
            repository.increaseQuantity(productId)
        }
    }

    private fun decreaseQuantity(productId: Int) {
        viewModelScope.launch {
            repository.decreaseQuantity(productId)
        }
    }
}
