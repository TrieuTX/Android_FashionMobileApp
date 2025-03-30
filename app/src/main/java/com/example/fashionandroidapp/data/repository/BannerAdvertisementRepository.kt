package com.example.fashionandroidapp.data.repository

import androidx.lifecycle.LiveData
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
            BannerAdvertisement(imageUrl = "https://static.vecteezy.com/system/resources/previews/008/174/590/non_2x/fashion-advertising-web-banner-illustration-vector.jpg"),
            BannerAdvertisement(imageUrl = "https://bizweb.dktcdn.net/100/439/387/articles/web-eng-1350x900.jpg?v=1668057046127"),
            BannerAdvertisement(imageUrl = "https://img.freepik.com/free-vector/flat-design-fashion-week-facebook-cover_23-2151084422.jpg"),
            BannerAdvertisement(imageUrl = "https://imgur.com/zYxDCQT"),
            BannerAdvertisement(imageUrl = "https://bizweb.dktcdn.net/100/439/387/articles/web-eng-1350x900.jpg?v=1668057046127"),
            BannerAdvertisement(imageUrl = "https://bizweb.dktcdn.net/100/439/387/articles/web-eng-1350x900.jpg?v=1668057046127")
        )
        bannerAdvertisementDao.insertBannerAdvertisementList(defaultProducts)
    }

    suspend fun isDatabaseEmpty(): Boolean {
        return bannerAdvertisementDao.getAllBannerAdvertisement().firstOrNull()?.isEmpty() ?: true
    }
}