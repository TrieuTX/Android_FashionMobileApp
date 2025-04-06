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

    suspend fun getProductById(id: Int): Product? = productDao.getProductById(id)

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    suspend fun insertDefaultProducts() {
        val defaultProducts = listOf(
            Product(name = "LeBron NXXT Genisus QS EP", price = 120.0, imageUrl = "image/product/Basketball/6.png", category = Category.Basketball),
            Product(name = "LeBron XXII 'Currency' EP", price = 5.869, imageUrl = "image/product/Basketball/1.png", category = Category.Basketball),
            Product(name = "Ja 2 'Foundation' EP", price = 3.519, imageUrl = "image/product/Basketball/2.png", category = Category.Basketball),
            Product(name = "Tatum 3 PF 'Zen'", price = 3.669, imageUrl = "image/product/Basketball/3.png", category = Category.Basketball),
            Product(name = "Tatum 3 PF 'Zero Days Off'", price = 3.669, imageUrl = "image/product/Basketball/4.png", category = Category.Basketball),
            Product(name = "Nike G.T. Cut 3 EP", price = 5.589, imageUrl = "image/product/Basketball/5.png", category = Category.Basketball),


            Product(name = "Nike Mercurial Vapor 1 RGN SE", price = 8.799, imageUrl = "image/product/Football/1.png", category = Category.Football),
            Product(name = "Nike Mercurial Superfly 10 Club", price = 1.889, imageUrl = "image/product/Football/2.png", category = Category.Football),
            Product(name = "Nike Phantom GX 2 Elite", price = 7.629, imageUrl = "image/product/Football/3.jpg", category = Category.Football),
            Product(name = "Nike Mercurial Superfly 10 Academy", price = 2.779, imageUrl = "image/product/Football/4.png", category = Category.Football),
            Product(name = "Nike Mercurial Vapor 16 Elite LV8", price = 7.629, imageUrl = "image/product/Football/5.png", category = Category.Football),
            Product(name = "Nike Tiempo Legend 10 Elite LV8", price = 7.039, imageUrl = "image/product/Football/6.png", category = Category.Football),

            Product(name = "Nike Free Golf NN", price = 3.829, imageUrl = "image/product/Golf/1.png", category = Category.Golf),
            Product(name = "Nike Air Max 1 '86 OG G'", price = 4.699, imageUrl = "image/product/Golf/2.png", category = Category.Golf),
            Product(name = "Nike Free Golf NN", price = 3.829, imageUrl = "image/product/Golf/3.png", category = Category.Golf),
            Product(name = "Nike Air Max 1 '86 OG G", price = 3.527, imageUrl = "image/product/Golf/4.png", category = Category.Golf),

            Product(name = "Nike Vaporfly 4", price = 7.629, imageUrl = "image/product/Running/1.png", category = Category.Running),
            Product(name = "Nike Vomero 18", price = 4.259, imageUrl = "image/product/Running/2.png", category = Category.Running),
            Product(name = "Nike Vomero 18", price = 4.259, imageUrl = "image/product/Running/3.png", category = Category.Running),
            Product(name = "Nike Pegasus Plus", price = 5.279, imageUrl = "image/product/Running/4.png", category = Category.Running),
            Product(name = "Nike Pegasus 41", price = 3.829, imageUrl = "image/product/Running/5.png", category = Category.Running),
            Product(name = "Nike Pegasus 41", price = 3.829, imageUrl = "image/product/Running/6.png", category = Category.Running),

            Product(name = "NikeCourt Lite 4", price = 2.189, imageUrl = "image/product/Tennis/1.png", category = Category.Tennis),
            Product(name = "NikeCourt Lite 4 Premium", price = 1.879, imageUrl = "image/product/Tennis/2.png", category = Category.Tennis),
            Product(name = "Nike GP Challenge Pro Premium", price = 3.519, imageUrl = "image/product/Tennis/3.png", category = Category.Tennis),
            Product(name = "Nike GP Challenge Pro", price = 3.239, imageUrl = "image/product/Tennis/4.png", category = Category.Tennis),
            Product(name = "Nike Zoom GP Challenge 1", price = 4.409, imageUrl = "image/product/Tennis/5.png", category = Category.Tennis),

            Product(name = "Nike Air Monarch IV", price = 1.909, imageUrl = "image/product/Training/1.png", category = Category.Training),
            Product(name = "Nike Metcon 1 OG", price = 4.409, imageUrl = "image/product/Training/2.png", category = Category.Training),
            Product(name = "Nike Metcon 9", price = 4.109, imageUrl = "image/product/Training/3.png", category = Category.Training),
            Product(name = "Nike Pegasus Plus", price = 2.499, imageUrl = "image/product/Training/4.png", category = Category.Training),
            Product(name = "Nike Flex Experience Run 12", price = 2.069, imageUrl = "image/product/Training/5.png", category = Category.Training),
        )
        productDao.insertProductList(defaultProducts)
    }

    suspend fun isDatabaseEmpty(): Boolean {
        return productDao.getAllProducts().firstOrNull()?.isEmpty() ?: true
    }
}
