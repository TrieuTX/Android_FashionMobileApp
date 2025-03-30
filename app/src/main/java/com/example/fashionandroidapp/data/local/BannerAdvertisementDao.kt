package com.example.fashionandroidapp.data.local

import androidx.room.*
import com.example.fashionandroidapp.data.model.BannerAdvertisement

import kotlinx.coroutines.flow.Flow

@Dao
interface BannerAdvertisementDao {
    @Query("SELECT * FROM banner_table ORDER BY id DESC")
    fun getAllBannerAdvertisement(): Flow<List<BannerAdvertisement>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBannerAdvertisement(bannerAdvertisement: BannerAdvertisement)

    @Delete
    suspend fun deleteBannerAdvertisement(bannerAdvertisement: BannerAdvertisement)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBannerAdvertisementList(bannerAdvertisements: List<BannerAdvertisement>)

    @Query("DELETE FROM banner_table")
    suspend fun clearAll()
}
