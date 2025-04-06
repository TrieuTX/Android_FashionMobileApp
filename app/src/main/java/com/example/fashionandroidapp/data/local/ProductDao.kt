package com.example.fashionandroidapp.data.local;

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fashionandroidapp.data.model.Product

import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table ORDER BY id DESC")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT DISTINCT category FROM product_table")
    fun getAllCategories(): Flow<List<String>>

    @Query("SELECT * FROM product_table WHERE category = :category")
    fun getProductsByCategory(category: String): Flow<List<Product>>

    @Query("SELECT COUNT(*) FROM product_table WHERE category = :category")
    fun countProductsByCategory(category: String): LiveData<Int>

    @Query("SELECT * FROM product_table WHERE id = :productId")
    suspend fun getProductById(productId: Int): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(products: List<Product>)

    @Query("DELETE FROM product_table")
    suspend fun clearAll()
}
