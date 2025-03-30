package com.example.fashionandroidapp.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fashionandroidapp.data.model.Product
import com.example.fashionandroidapp.data.model.Category
import com.example.fashionandroidapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.Typography.dagger

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {
//    val categories: StateFlow<List<Product>> = repository.allProducts
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getAllCategories(): Flow<List<String>> {
        println("getAllCategories")
        return repository.getAllCategories()
    }

    fun getProductsByCategory(category: String): Flow<List<Product>> {
        return repository.getProductsByCategory(category)
    }

    fun get4ProductsByCategory(category: String): Flow<List<Product>> {
        val list =  getProductsByCategory(category).map { productList -> productList.take(4) }
        return list;
    }

    fun  getNameProduct(product: Product) : String{
        return product.name
    }

    fun countProductsByCategory(category: String): LiveData<Int> {
        return repository.countProductsByCategory(category)
    }

//    fun addProduct(name: String, price: Double, imageUrl: String, category: String) {
//        viewModelScope.launch {
//            repository.insertProduct(Product(name = name, price = price, imageUrl = imageUrl, category = category))
//        }
//    }

//    fun deleteProduct(product: Product) {
//        viewModelScope.launch {
//            repository.deleteProduct(product)
//        }
//    }
}
