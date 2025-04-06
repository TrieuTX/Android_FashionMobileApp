package com.example.fashionandroidapp.di

import android.content.Context
import androidx.room.Room
import com.example.fashionandroidapp.data.local.AppDatabase
import com.example.fashionandroidapp.data.local.BannerAdvertisementDao
import com.example.fashionandroidapp.data.local.CartProductDao
import com.example.fashionandroidapp.data.local.ProductDao
import com.example.fashionandroidapp.data.model.BannerAdvertisement
import com.example.fashionandroidapp.data.repository.BannerAdvertisementRepository
import com.example.fashionandroidapp.data.repository.CartProductRepository
import com.example.fashionandroidapp.data.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    @Provides
    fun provideBannerAdvertisementDao(database: AppDatabase) : BannerAdvertisementDao {
        return database.bannerAdvertisementDao()
    }

    @Provides
    fun provideBannerAdvertisementRepository(dao: BannerAdvertisementDao): BannerAdvertisementRepository {
        return BannerAdvertisementRepository(dao)
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideProductRepository(dao: ProductDao): ProductRepository {
        return ProductRepository(dao)
    }

    @Provides
    fun provideCartProductDao(database: AppDatabase) : CartProductDao {
        return database.cartProductDao()
    }

    @Provides
    fun provideCartProductRepository(dao:  CartProductDao): CartProductRepository {
        return CartProductRepository(dao)
    }
}
