package com.example.fashionandroidapp

import android.app.Application
import com.example.fashionandroidapp.data.local.BannerAdvertisementDao
import com.example.fashionandroidapp.data.local.CartProductDao
import com.example.fashionandroidapp.data.local.ProductDao
import com.example.fashionandroidapp.data.model.BannerAdvertisement
import com.example.fashionandroidapp.data.repository.BannerAdvertisementRepository
import com.example.fashionandroidapp.data.repository.CartProductRepository
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
    lateinit var productRepository: ProductRepository
    @Inject
    lateinit var productDao: ProductDao

    @Inject
    lateinit var bannerAdvertisementRepository: BannerAdvertisementRepository
    @Inject
    lateinit var bannerAdvertisementDao: BannerAdvertisementDao

    @Inject
    lateinit var cartProductDao: CartProductDao

    @Inject
    lateinit var cartProductRepository: CartProductRepository

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            delay(100)

            productDao.clearAll()
            productRepository.insertDefaultProducts()

            bannerAdvertisementDao.clearAll()
            bannerAdvertisementRepository.insertDefaultBannerAdvertisement()
        }
    }
}
