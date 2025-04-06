package com.example.fashionandroidapp.data.repository

import com.example.fashionandroidapp.data.local.CartProductDao
import com.example.fashionandroidapp.data.model.CartProduct
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartProductRepository @Inject constructor(private val dao: CartProductDao) {

    val allCartProducts: Flow<List<CartProduct>> = dao.getAllCartProducts()

    suspend fun insert(cartProduct: CartProduct) {
        dao.insertCartProduct(cartProduct)
    }

    suspend fun isProductInCart(productId: Int): Boolean {
        return dao.isProductInCart(productId)
    }

    suspend fun update(cartProduct: CartProduct) {
        dao.updateCartProduct(cartProduct)
    }

    suspend fun getCartProductByProductId(productId: Int) : CartProduct?{
        return dao.getCartProductByProductId(productId)
    }


    suspend fun delete(cartProduct: CartProduct) {
        dao.deleteCartProduct(cartProduct)
    }

    suspend fun increaseQuantity(productId: Int) {
        dao.increaseQuantity(productId)
    }

    suspend fun getQuantityByProductId(productId: Int): Int? {
        return dao.getQuantityByProductId(productId)
    }


    suspend fun decreaseQuantity(productId: Int) {
        dao.decreaseQuantity(productId)
    }

    suspend fun clearCart() {
        dao.clearCart()
    }
}