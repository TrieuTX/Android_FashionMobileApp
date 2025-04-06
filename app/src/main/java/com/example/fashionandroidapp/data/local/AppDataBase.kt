package com.example.fashionandroidapp.data.local

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fashionandroidapp.data.model.BannerAdvertisement
import com.example.fashionandroidapp.data.model.CartProduct
import com.example.fashionandroidapp.data.model.Product
import com.example.fashionandroidapp.data.model.Category

@Database(entities = [Product::class, BannerAdvertisement::class, CartProduct::class], version = 1, exportSchema = false)
@TypeConverters(CategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun bannerAdvertisementDao(): BannerAdvertisementDao
    abstract fun cartProductDao(): CartProductDao

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null

//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "product_database"
//                )
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
   // }
}


class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: Category): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(category: String): Category {
        return Category.valueOf(category)
    }
}

//class ImageUrlConverter {
//    @TypeConverter
//    fun fromCategory(category: Category): String {
//        return category.name
//    }
//
//    @TypeConverter
//    fun toCategory(category: String): Category {
//        return Category.valueOf(category)
//    }
//}

