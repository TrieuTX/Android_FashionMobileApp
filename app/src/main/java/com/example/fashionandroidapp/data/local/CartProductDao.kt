package com.example.fashionandroidapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fashionandroidapp.data.model.CartProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface CartProductDao {

    @Query("SELECT * FROM cart_product_table")
    fun getAllCartProducts(): Flow<List<CartProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartProduct(cartProduct: CartProduct)

    @Query("SELECT EXISTS(SELECT 1 FROM cart_product_table WHERE productId = :productId)")
    suspend fun isProductInCart(productId: Int): Boolean

    @Update
    suspend fun updateCartProduct(cartProduct: CartProduct)

    @Query("SELECT * FROM cart_product_table WHERE productId = :productId LIMIT 1")
    suspend fun getCartProductByProductId(productId: Int): CartProduct?

    @Delete
    suspend fun deleteCartProduct(cartProduct: CartProduct)

    @Query("UPDATE cart_product_table SET quantity = quantity + 1 WHERE productId = :productId")
    suspend fun increaseQuantity(productId: Int)

    @Query("SELECT quantity FROM cart_product_table WHERE productId = :productId")
    suspend fun getQuantityByProductId(productId: Int): Int?

    @Query("UPDATE cart_product_table SET quantity = quantity - 1 WHERE productId = :productId")
    suspend fun decreaseQuantity(productId: Int)

    @Query("DELETE FROM cart_product_table")
    suspend fun clearCart()
}