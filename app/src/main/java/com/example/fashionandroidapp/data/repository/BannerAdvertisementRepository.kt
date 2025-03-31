package com.example.fashionandroidapp.data.repository

import androidx.lifecycle.LiveData
import com.example.fashionandroidapp.R
import com.example.fashionandroidapp.data.local.BannerAdvertisementDao
import com.example.fashionandroidapp.data.local.ProductDao
import com.example.fashionandroidapp.data.model.BannerAdvertisement
import com.example.fashionandroidapp.data.model.Category
import com.example.fashionandroidapp.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class BannerAdvertisementRepository @Inject constructor(private val bannerAdvertisementDao: BannerAdvertisementDao) {

    fun getAllBannerAdvertisement(): Flow<List<BannerAdvertisement>> {
        return bannerAdvertisementDao.getAllBannerAdvertisement()
    }

    suspend fun insertBannerAdvertisement(bannerAdvertisement: BannerAdvertisement) {
        bannerAdvertisementDao.insertBannerAdvertisement(bannerAdvertisement)
    }

    suspend fun deleteBannerAdvertisement(bannerAdvertisement: BannerAdvertisement) {
        bannerAdvertisementDao.deleteBannerAdvertisement(bannerAdvertisement)
    }

    suspend fun insertDefaultBannerAdvertisement() {
        val defaultProducts = listOf(
            BannerAdvertisement(imageUrl = "image/banner/5.jpg"),
            BannerAdvertisement(imageUrl = "image/banner/4.jpg"),
            BannerAdvertisement(imageUrl = "image/banner/3.jpg"),
            BannerAdvertisement(imageUrl = "image/banner/1.webp")
        )
        bannerAdvertisementDao.insertBannerAdvertisementList(defaultProducts)
    }

    suspend fun isDatabaseEmpty(): Boolean {
        return bannerAdvertisementDao.getAllBannerAdvertisement().firstOrNull()?.isEmpty() ?: true
    }
}