package com.example.fashionandroidapp.data.repository


import androidx.lifecycle.LiveData
import com.example.fashionandroidapp.data.local.ProductDao
import com.example.fashionandroidapp.data.model.Category
import com.example.fashionandroidapp.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) {
    val allProducts: Flow<List<Product>> = productDao.getAllProducts()

    fun getAllCategories(): Flow<List<String>> {
        return productDao.getAllCategories()
    }

    fun getProductsByCategory(category: String): Flow<List<Product>> {
        return productDao.getProductsByCategory(category)
    }

    fun countProductsByCategory(category: String): LiveData<Int> {
        return productDao.countProductsByCategory(category)
    }

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    suspend fun insertDefaultProducts() {
        val defaultProducts = listOf(
            Product(name = "Nike Air Max 2", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Shoes),
            Product(name = "Adidas Ultraboost 2", price = 140.0, imageUrl = "Image/nike2.jpg", category = Category.Shoes),
            Product(name = "Nike Air Max 1", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Shoes),
            Product(name = "Adidas Ultraboost 1", price = 140.0, imageUrl = "Image/nike2.jpg", category = Category.Shoes),
            Product(name = "Nike Air Max 5", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Shoes),
            Product(name = "Gucci Bag 4", price = 400.0, imageUrl = "Image/nike.jpg", category = Category.Bags),
            Product(name = "Gucci Bag 3", price = 400.0, imageUrl = "Image/nike.jpg", category = Category.Bags),
            Product(name = "Gucci Bag 2", price = 400.0, imageUrl = "Image/nike.jpg", category = Category.Bags),
            Product(name = "Gucci Bag 1", price = 400.0, imageUrl = "Image/nike.jpg", category = Category.Bags),
            Product(name = "Rolex Watch 4", price = 5000.0, imageUrl = "Image/nike2.jpg", category = Category.Watch),
            Product(name = "Rolex Watch 3", price = 5000.0, imageUrl = "Image/nike2.jpg", category = Category.Watch),
            Product(name = "Rolex Watch 2", price = 5000.0, imageUrl = "Image/nike2.jpg", category = Category.Watch),
            Product(name = "Rolex Watch 1", price = 5000.0, imageUrl = "Image/nike2.jpg", category = Category.Watch),
            Product(name = " Hoodie1", price = 60.0, imageUrl = "Image/nike.jpg", category = Category.Hoodies),
            Product(name = "H&M1Hoodie", price = 60.0, imageUrl = "Image/nike.jpg", category = Category.Hoodies),
            Product(name = "H&M 2 Hoodie", price = 60.0, imageUrl = "Image/nike.jpg", category = Category.Hoodies),
            Product(name = "H&M 3Hoodie", price = 60.0, imageUrl = "Image/nike.jpg", category = Category.Hoodies),
            Product(name = "Nike Air 5 Max 2", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Lingerie),
            Product(name = "Nike Air1  Max 2", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Lingerie),
            Product(name = "Nike Air 1 Max 2", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Lingerie),
            Product(name = "Nike Air  Max 2", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Lingerie),
            Product(name = "uniquino  1", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Clothing),
            Product(name = "uniquino 2 ", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Clothing),
            Product(name = "uniquino 3 ", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Clothing),
            Product(name = "uniquino  4", price = 120.0, imageUrl = "Image/nike.jpg", category = Category.Clothing)


        )
        productDao.insertProductList(defaultProducts)
    }

    suspend fun isDatabaseEmpty(): Boolean {
        return productDao.getAllProducts().firstOrNull()?.isEmpty() ?: true
    }
}
