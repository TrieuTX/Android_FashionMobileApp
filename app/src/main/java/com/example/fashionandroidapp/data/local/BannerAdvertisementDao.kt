package com.example.fashionandroidapp.data.local//package com.example.createandthemecardlayout.data.local
//
//import androidx.room.*
//
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface BannerAdvertisement {
//    @Query("SELECT * FROM banner_table ORDER BY id DESC")
//    fun getAllBanner(): Flow<List<BannerAdvertisement>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertBanner(bannerAdvertisement: BannerAdvertisement)
//
//    @Delete
//    suspend fun deleteBanner(bannerAdvertisement: BannerAdvertisement)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertBannerList(bannerAdvertisements: List<BannerAdvertisement>)
//
//    @Query("DELETE FROM banner_table")
//    suspend fun clearAll()
//}
