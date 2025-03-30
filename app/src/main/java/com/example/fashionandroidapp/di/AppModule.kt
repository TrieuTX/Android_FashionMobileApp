package com.example.fashionandroidapp.di

import android.content.Context
import androidx.room.Room
import com.example.fashionandroidapp.data.local.AppDatabase
import com.example.fashionandroidapp.data.local.ProductDao
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
            "product_db"
        ).build()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideProductRepository(dao: ProductDao): ProductRepository {
        return ProductRepository(dao)
    }
}
