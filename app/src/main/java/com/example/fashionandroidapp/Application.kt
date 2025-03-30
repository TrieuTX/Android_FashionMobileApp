package com.example.fashionandroidapp

import android.app.Application
import com.example.fashionandroidapp.data.local.ProductDao
import com.example.fashionandroidapp.data.repository.ProductRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class Application : Application() {
    @Inject
    lateinit var repository: ProductRepository
    @Inject
    lateinit var productDao: ProductDao
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            delay(100)
            //if (repository.isDatabaseEmpty()) {
            productDao.clearAll()
            repository.insertDefaultProducts()
           //}
        }
    }
}
